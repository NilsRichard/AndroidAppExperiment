package com.example.mmm_tp1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Fragment3 extends Fragment implements UserInfoAdapter.UserInfoAdapterListener {

    private RecyclerView recyclerView;
    private List<UserInfo> contactList;
    private UserInfoAdapter mAdapter;
    private SearchView searchView;

    private OnFragment3InteractionListener mListener;

    public Fragment3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment3, container, false);

        contactList = new ArrayList<>();

        SharedInfoVM model = new ViewModelProvider(requireActivity()).get(SharedInfoVM.class);
        model.getUserInfos().observe(getViewLifecycleOwner(), data -> {
            contactList.addAll(data);
        });

        // handle the recycler view
        recyclerView = v.findViewById(R.id.recyclerView);
        mAdapter = new UserInfoAdapter(contactList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        // handle the floating action menu
        FloatingActionButton myFab = v.findViewById(R.id.nouveauClient);
        myFab.setOnClickListener(v1 -> {
            mListener.onFragment3Interaction();
        });

        return v;
    }


    @Override
    public void onAttach(Context context) {
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
