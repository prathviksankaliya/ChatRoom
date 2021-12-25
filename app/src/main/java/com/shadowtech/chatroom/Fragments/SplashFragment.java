package com.shadowtech.chatroom.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shadowtech.chatroom.MainActivity;
import com.shadowtech.chatroom.Model.UserProfile;
import com.shadowtech.chatroom.R;
import com.shadowtech.chatroom.databinding.FragmentSplashBinding;

import java.util.Objects;

public class SplashFragment extends Fragment {

    public SplashFragment() {
        // Required empty public constructor
    }
    FirebaseAuth auth;
    FragmentSplashBinding binding;
    FirebaseDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(getLayoutInflater());
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


            Thread thread = new Thread()
            {
                @Override
                public void run() {
                    super.run();
                    try {
                        sleep(1100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finally {

                        if(auth.getCurrentUser() == null)
                        {
                            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.frUserDetailsContainer , new SignInFragment());
                            fragmentTransaction.commit();
                        }
                        else
                        {
                            Intent intent = new Intent(getContext() , MainActivity.class);
                            startActivity(intent);
                            requireActivity().finishAffinity();

                        }
                    }
                }
            };
            thread.start();
        return binding.getRoot();

    }
}