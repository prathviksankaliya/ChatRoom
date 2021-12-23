package com.shadowtech.chatroom.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.shadowtech.chatroom.MainActivity;
import com.shadowtech.chatroom.R;
import com.shadowtech.chatroom.databinding.FragmentOtpVerifyBinding;

import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class OtpVerifyFragment extends Fragment {

    public OtpVerifyFragment() {
        // Required empty public constructor
    }
    FirebaseAuth auth;
    private String verifyId;
    FragmentOtpVerifyBinding binding;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOtpVerifyBinding.inflate(getLayoutInflater());
        auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Sending OTP ...");


        SharedPreferences spf = requireContext().getSharedPreferences("UserPhoneNumber" , Context.MODE_PRIVATE);
        String UserNumber = "+91"+spf.getString("Phonenumber" , "NULL");
        binding.txVarifyNumber.setText("Verify "+UserNumber);

        sendVerificationCode(UserNumber);
        progressDialog.show();

        binding.btnVarifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Objects.requireNonNull(binding.otpView.getOTP()).toString().isEmpty())
                {
                    Toast.makeText(getContext(), "Please Enter OTP ", Toast.LENGTH_SHORT).show();
                }
                else {
                    verifyCode(binding.otpView.getOTP().toString());
                }
            }
        });

        return binding.getRoot();
    }
    private void sendVerificationCode(String number)
    {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                .setActivity(requireActivity())
                .setPhoneNumber(number)
                .setTimeout(40L , TimeUnit.SECONDS)
                .setCallbacks(callbacks)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            final String Code = phoneAuthCredential.getSmsCode();
            if(Code != null)
            {
                binding.otpView.setOTP(Code);
                verifyCode(Code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            progressDialog.dismiss();
            verifyId = s;
        }
    };
    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verifyId , code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential)
    {
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {

                    Intent i = new Intent(getContext() , MainActivity.class);
                    startActivity(i);
                    requireActivity().finish();
                }
                else
                {
                    Toast.makeText(getContext(), ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}