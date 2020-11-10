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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Fragment3 extends Fragment implements UserInfoAdapter.UserInfoAdapterListener {

    private DBViewModel viewModel;

    private OnFragment3InteractionListener mListener;

    public Fragment3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment3, container, false);

        // handle the recycler view
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final UserInfoAdapter adapter = new UserInfoAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        // on crée une instance de notre ViewModel
        //viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        viewModel = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()).create(DBViewModel.class);

        // et on ajoute un observer sur les utilisateurs...
        viewModel.getAllUsers().observe(getViewLifecycleOwner(), adapter::setUserInfos);

        // handle the floating action menu
        FloatingActionButton myFab = v.findViewById(R.id.nouveauClient);
        myFab.setOnClickListener(v1 -> mListener.onFragment3Interaction());

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.delete(adapter.getUserAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(), "User info deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        return v;
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

    public void addUserInfo(UserInfo userInfo) {
        Log.i("TAG","Adding a view...");
        viewModel.insert(userInfo);
    }

}
