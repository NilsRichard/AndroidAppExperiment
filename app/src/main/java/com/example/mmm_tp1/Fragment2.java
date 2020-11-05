package com.example.mmm_tp1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {

    protected static final String EXTRA_NOM = "nom";
    protected static final String EXTRA_PRENOM = "prenom";
    protected static final String EXTRA_VILLE = "villeNaissance";
    protected static final String EXTRA_DATE = "dateNaissance";
    protected static final String EXTRA_TEL = "tel";

    private String nom;
    private String prenom;
    private String villeNaissance;
    private String dateNaissance;
    private String tel;

    private OnFragment2InteractionListener mListener;

    public Fragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment2, container, false);

        ((TextView) v.findViewById(R.id.textView3)).setText(nom);
        ((TextView) v.findViewById(R.id.textView4)).setText(prenom);
        ((TextView) v.findViewById(R.id.textView5)).setText(villeNaissance);
        ((TextView) v.findViewById(R.id.textView6)).setText(dateNaissance);
        ((TextView) v.findViewById(R.id.textView7)).setText(tel);

        return v;
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
        void onFragment2Interaction(Uri uri);
    }
}
