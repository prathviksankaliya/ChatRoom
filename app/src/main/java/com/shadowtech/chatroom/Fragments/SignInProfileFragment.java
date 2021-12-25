package com.shadowtech.chatroom.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.shadowtech.chatroom.MainActivity;
import com.shadowtech.chatroom.Model.UserProfile;
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
    Uri selectedImg;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentSignInProfileBinding.inflate(getLayoutInflater());
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Saved Profile .....");


        binding.btnSignInProfileSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.edSignInProfileName.getText().toString().isEmpty()) {
                    binding.edSignInProfileName.setError("Please Enter Name");
                    binding.edSignInProfileName.requestFocus();
                } else if (binding.edSignInProfileAbout.getText().toString().isEmpty()) {
                    binding.edSignInProfileAbout.setError("Please Enter About Yourself");
                    binding.edSignInProfileAbout.requestFocus();
                } else {
                    progressDialog.show();
                    String UserProfileName = binding.edSignInProfileName.getText().toString();
                    String UserProfileAbout = binding.edSignInProfileAbout.getText().toString();

                    if (selectedImg != null) {
                        StorageReference reference = storage.getReference().child("Profiles").child(auth.getUid());
                        reference.putFile(selectedImg).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                if (task.isSuccessful()) {
                                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            String imageurl = uri.toString();
                                            String UId = auth.getUid();
                                            String phone = auth.getCurrentUser().getPhoneNumber();

                                            UserProfile userProfile = new UserProfile(UId, UserProfileName, UserProfileAbout, phone, imageurl);
                                            database.getReference().child("Users").child(auth.getUid()).setValue(userProfile).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    progressDialog.dismiss();
                                                    Intent intent = new Intent(getContext(), MainActivity.class);
                                                    startActivity(intent);
                                                    requireActivity().finishAffinity();
                                                }
                                            });
                                        }
                                    });
                                }
                            }
                        });
                    } else {
                        String UId = auth.getUid();
                        String phone = auth.getCurrentUser().getPhoneNumber();

                        UserProfile userProfile = new UserProfile(UId, UserProfileName, UserProfileAbout, phone, "NoImage");
                        database.getReference().child("Users").child(auth.getUid()).setValue(userProfile).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                progressDialog.dismiss();
                                Intent intent = new Intent(getContext(), MainActivity.class);
                                startActivity(intent);
                                requireActivity().finishAffinity();
                            }
                        });

                    }
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
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            if (data.getData() != null) {
                                binding.profileImage.setImageURI(data.getData());
                                selectedImg = data.getData();
                            }
                        }

                    }
                }
            });
}
