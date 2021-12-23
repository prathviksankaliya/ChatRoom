package com.shadowtech.chatroom.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.shadowtech.chatroom.MainActivity;
import com.shadowtech.chatroom.R;
import com.shadowtech.chatroom.databinding.FragmentSignInBinding;

import java.util.Objects;
import java.util.concurrent.TimeUnit;


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
                    SharedPreferences spf = requireContext().getSharedPreferences("UserPhoneNumber" , Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = spf.edit();
                    editor.putString("Phonenumber" , signInUserPhone);
                    editor.apply();
                    FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frUserDetailsContainer , new OtpVerifyFragment());
                    fragmentTransaction.commit();
                }

            }
        });
        return binding.getRoot();
    }


}
