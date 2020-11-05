package com.example.mmm_tp1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment {
    private OnFragment1InteractionListener mListener;

    // my Shared data between the fragments
    private SharedInfoVM myData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myData = new ViewModelProvider(requireActivity()).get(SharedInfoVM.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment1, container, false);

        v.findViewById(R.id.button).setOnClickListener(this::onValidate);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                ((EditText) getView().findViewById(R.id.editNomText)).setText("");
                ((EditText) getView().findViewById(R.id.editPrenomText)).setText("");
                ((EditText) getView().findViewById(R.id.editVilleText)).setText("");
                ((EditText) getView().findViewById(R.id.editTextDate)).setText("");
                ((EditText) getView().findViewById(R.id.editTel)).setText("");
                return true;
            case R.id.item2:
                getView().findViewById(R.id.editTel).setVisibility(View.VISIBLE);
                return true;
            case R.id.item3:
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://fr.wikipedia.org/wiki/" + ((EditText) getView().findViewById(R.id.editVilleText)).getText()));
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onValidate(View view) {
        String nom = ((EditText) getView().findViewById(R.id.editNomText)).getText().toString();
        String prenom = ((EditText) getView().findViewById(R.id.editPrenomText)).getText().toString();
        String villeNaissance = ((EditText) getView().findViewById(R.id.editVilleText)).getText().toString();
        String dateNaissance = ((EditText) getView().findViewById(R.id.editTextDate)).getText().toString();
        String tel = ((EditText) getView().findViewById(R.id.editTel)).getText().toString();

        if (mListener != null) {
            myData.addUserInfo(new UserInfo(nom, prenom, villeNaissance, dateNaissance, tel));
            mListener.onFragment1Interaction();
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragment1InteractionListener) {
            mListener = (OnFragment1InteractionListener) context;
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
     * Old version in tp1
     *
     * @param view
     */
    public void toastOnValidate(View view) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Bonjour ");

        String nom = ((EditText) getView().findViewById(R.id.editNomText)).getText().toString();
        String prenom = ((EditText) getView().findViewById(R.id.editPrenomText)).getText().toString();
        String villeNaissance = ((EditText) getView().findViewById(R.id.editVilleText)).getText().toString();
        String dateNaissance = ((EditText) getView().findViewById(R.id.editTextDate)).getText().toString();

        stringBuilder.append(nom).append(" ").append(prenom).append(" né à ").append(villeNaissance).append(" à ").append(dateNaissance);

        Toast.makeText(getActivity(), stringBuilder.toString(), Toast.LENGTH_SHORT).show();
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
    public interface OnFragment1InteractionListener {
        void onFragment1Interaction();
    }
}
