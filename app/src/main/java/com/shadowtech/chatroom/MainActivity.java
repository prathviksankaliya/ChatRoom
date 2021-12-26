package com.shadowtech.chatroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.shadowtech.chatroom.Fragments.DashboardFragment;
import com.shadowtech.chatroom.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
        ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frMainActivityContainer , new DashboardFragment());
        fragmentTransaction.commit();


    }
}