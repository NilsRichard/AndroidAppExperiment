package com.example.mmm_tp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Fragment1.OnFragment1InteractionListener, Fragment2.OnFragment2InteractionListener {
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    }

    @Override
    public void onFragment2Interaction(Uri uri) {
        navController.navigate(R.id.action_fragment2_to_fragment1);
    }

    @Override
    public void onFragment1Interaction(String nom, String prenom, String villeN, String dateN, String tel) {
        navController.navigate(R.id.action_fragment1_to_fragment2);
    }
}