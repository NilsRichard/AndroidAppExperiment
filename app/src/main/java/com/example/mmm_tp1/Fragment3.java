package com.example.mmm_tp1;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fragment3 extends Fragment implements UserInfoAdapter.UserInfoAdapterListener {

    private OnFragment3InteractionListener mListener;

    public Fragment3() {
        // Required empty public constructor
    }


    public static final String ANONYMOUS = "anonymous";
    private String mUsername = ANONYMOUS;
    public static final int RC_SIGN_IN = 1;

    UserInfoAdapter adapter;

    // STEP 1 : make a reference to the database...
    private FirebaseDatabase mFireDataBase;
    private DatabaseReference mContactsDatabaseReference;

    //STEP 4: child event lister.
    private ChildEventListener mChildEventListener;

    //STEP 7: Auth
    private FirebaseAuth mFireBaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStatListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment3, container, false);

        mUsername = ANONYMOUS;

        // STEP 2 : access the DB...
        mFireDataBase = FirebaseDatabase.getInstance();

        // STEP 2.1: and from the DB, get a reference on the child node "contacts"
        mContactsDatabaseReference = mFireDataBase.getReference().child("userInfos");

        // STEP 2.2: get the recycler view
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        // STEP 2.3: create and set the adapter
        adapter = new UserInfoAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        // STEP 3: enable adding a contact to Firebase
        activateAddingUserInfo(v);

        // STEP 4: listen to any change on the DB
        enableUpdatesFromDB();

        // STEP 5: Enable removing a contact
        activateRemovingUserInfo(recyclerView);

        // STEP 7: Adding authentication
        mFireBaseAuth = FirebaseAuth.getInstance();
        activateAutentication();

        return v;
    }

    // STEP 3: enable adding a contact to Firebase
    public void activateAddingUserInfo(View v) {
        FloatingActionButton myFab = (FloatingActionButton) v.findViewById(R.id.nouveauClient);
        myFab.setOnClickListener(v1 -> mListener.onFragment3Interaction());
    }

    // STEP 4: listen to any change on the DB
    public void enableUpdatesFromDB() {
        if (mUsername != null) {
            Log.i("enableUpdatesFromDB", "username ok");
            if (!mUsername.equals(ANONYMOUS)) {
                Log.i("enableUpdatesFromDB", "username non anonymous");

                if (mChildEventListener == null) {
                    mChildEventListener = new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
                            UserInfo userInfo = dataSnapshot.getValue(UserInfo.class);
                            // don't forget to set the key to identify the UserInfo!
                            assert userInfo != null;
                            userInfo.setUid(dataSnapshot.getKey());
                            adapter.addUserInfo(userInfo);
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String s) {
                            UserInfo userInfo = dataSnapshot.getValue(UserInfo.class);
                            assert userInfo != null;
                            userInfo.setUid(dataSnapshot.getKey());
                            adapter.updateUserInfo(userInfo);
                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                            //UserInfo msg = dataSnapshot.getValue(UserInfo.class);
                            // don't forget to set the key to identify the UserInfo!
                            adapter.removeUserInfoWithId(dataSnapshot.getKey());
                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String s) {
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    };
                    mContactsDatabaseReference.addChildEventListener(mChildEventListener);
                }
            }
        } else
            Log.i("enableUpdatesFromDB", "no username ok");
    }

    // STEP 5: Enable removing a contact
    public void activateRemovingUserInfo(RecyclerView recyclerView) {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // STEP 5.1: get a reference to the selected entity
                UserInfo remove = adapter.getUserInfos().get(viewHolder.getAdapterPosition());

                // STEP 5.2: use it's unique ID to remove it from Firebase
                mContactsDatabaseReference.child(remove.getUid()).removeValue();

                Toast.makeText(getActivity(), "UserInfo deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
    }

    // STEP 7: Activate Autentication
    public void activateAutentication() {
        mAuthStatListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // signed in
                    onSignedIn(user.getDisplayName());
                } else {
                    // not signed int
                    onSignedOut();

                    List<AuthUI.IdpConfig> providers = Arrays.asList(
                            new AuthUI.IdpConfig.EmailBuilder().build(),
                            new AuthUI.IdpConfig.GoogleBuilder().build());

                    startActivityForResult(AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(providers)
                                    .build(),
                            RC_SIGN_IN);

                }
            }
        };
        mFireBaseAuth.addAuthStateListener(mAuthStatListener);
    }

    void onSignedIn(String name) {
        mUsername = name;
        enableUpdatesFromDB();
    }

    void onSignedOut() {
        mUsername = ANONYMOUS;
        adapter.getUserInfos().clear();
        if (mChildEventListener != null) {
            mContactsDatabaseReference.removeEventListener(mChildEventListener);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragment3InteractionListener) {
            mListener = (OnFragment3InteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onUserInfoSelected(UserInfo userInfo) {
        Toast.makeText(getActivity(), "Selected: " + userInfo.getNom() + ", " + userInfo.getPrenom(), Toast.LENGTH_LONG).show();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragment3InteractionListener {
        void onFragment3Interaction();
    }

}
