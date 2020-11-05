package com.example.mmm_tp1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class Fragment2 extends Fragment {
    private OnFragment2InteractionListener mListener;

    public Fragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment2, container, false);
        return v;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedInfoVM model = new ViewModelProvider(requireActivity()).get(SharedInfoVM.class);
        model.getData().observe(getViewLifecycleOwner(), data -> {
            // Update the UI.
            ((TextView) view.findViewById(R.id.textView3)).setText(data.nom);
            ((TextView) view.findViewById(R.id.textView4)).setText(data.prenom);
            ((TextView) view.findViewById(R.id.textView5)).setText(data.villeNaissance);
            ((TextView) view.findViewById(R.id.textView6)).setText(data.dateNaissance);
            ((TextView) view.findViewById(R.id.textView7)).setText(data.tel);
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragment2InteractionListener) {
            mListener = (OnFragment2InteractionListener) context;
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
    public interface OnFragment2InteractionListener {
        // TODO: Update argument type and name
        void onFragment2Interaction();
    }
}
