package com.shadowtech.chatroom.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shadowtech.chatroom.R;
import com.shadowtech.chatroom.databinding.FragmentSignInBinding;


public class SignInFragment extends Fragment {


    public SignInFragment() {
        // Required empty public constructor
    }
    FragmentSignInBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(getLayoutInflater());

        binding.btnVerifyNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(binding.edPhoneNumber.getText().toString().length() != 10)
                {
                    binding.edPhoneNumber.setError("Please Check Your Number");
                    binding.edPhoneNumber.requestFocus();
                }
                else {
                    String signInUserPhone = binding.edPhoneNumber.getText().toString();
                    Toast.makeText(getContext(), ""+signInUserPhone, Toast.LENGTH_SHORT).show();
                }
            }
        });
        return binding.getRoot();
    }
}
