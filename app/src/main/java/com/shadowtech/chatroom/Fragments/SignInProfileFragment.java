package com.shadowtech.chatroom.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.shadowtech.chatroom.R;
import com.shadowtech.chatroom.databinding.FragmentSignInProfileBinding;

import java.util.Objects;


public class SignInProfileFragment extends Fragment {



    public SignInProfileFragment() {
        // Required empty public constructor
    }

    FragmentSignInProfileBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignInProfileBinding.inflate(getLayoutInflater());
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        binding.btnSignInProfileSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Objects.requireNonNull(binding.edSignInProfileName.getText()).toString().isEmpty())
                {
                    binding.edSignInProfileName.setError("Please Enter Name");
                    binding.edSignInProfileName.requestFocus();
                }
                else if(Objects.requireNonNull(binding.edSignInProfileAbout.getText()).toString().isEmpty())
                {
                    binding.edSignInProfileAbout.setError("Please Enter About Yourself");
                    binding.edSignInProfileAbout.requestFocus();
                }
                else {
                   String UserProfileName =  binding.edSignInProfileName.getText().toString();
                   String UserProfileAbout =  binding.edSignInProfileAbout.getText().toString();

                }

            }
        });
        binding.imSignInProfileCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                someActivityResultLauncher.launch(intent);
            }
        });
        return binding.getRoot();
    }
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            , new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK)
                    {
                        Intent data = result.getData();
                        if(data != null)
                        {
                            if(data.getData() != null)
                            {
                                binding.profileImage.setImageURI(data.getData());
                            }
                        }

                    }
                }
            });
}