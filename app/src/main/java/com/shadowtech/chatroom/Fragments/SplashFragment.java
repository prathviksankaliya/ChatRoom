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

import com.google.firebase.auth.FirebaseAuth;
import com.shadowtech.chatroom.MainActivity;
import com.shadowtech.chatroom.R;
import com.shadowtech.chatroom.databinding.FragmentSplashBinding;

import java.util.Objects;

public class SplashFragment extends Fragment {

    public SplashFragment() {
        // Required empty public constructor
    }
    FirebaseAuth auth;
    FragmentSplashBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(getLayoutInflater());
        auth = FirebaseAuth.getInstance();
        SharedPreferences spf = requireContext().getSharedPreferences("UserPhoneNumber" , Context.MODE_PRIVATE);
        String number = spf.getString("Phonenumber" , null);

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
                        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                        if(number.isEmpty())
                        {
                            fragmentTransaction.replace(R.id.frUserDetailsContainer , new SignInFragment());
                            fragmentTransaction.commit();
                        }
                        else {
                            fragmentTransaction.replace(R.id.frUserDetailsContainer , new SignInProfileFragment());
                            fragmentTransaction.commit();
                        }

                    }
                }
            };
            thread.start();
        return binding.getRoot();

    }
}