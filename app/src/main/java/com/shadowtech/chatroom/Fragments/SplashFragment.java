package com.shadowtech.chatroom.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shadowtech.chatroom.R;
import com.shadowtech.chatroom.databinding.FragmentSplashBinding;

public class SplashFragment extends Fragment {

    public SplashFragment() {
        // Required empty public constructor
    }

    FragmentSplashBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(getLayoutInflater());

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
                        fragmentTransaction.replace(R.id.frUserDetailsContainer , new SignInFragment());
                        fragmentTransaction.commit();
                    }
                }
            };
            thread.start();
        return binding.getRoot();

    }
}