package com.shadowtech.chatroom.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.shadowtech.chatroom.Adapter.vpAdapter;
import com.shadowtech.chatroom.R;
import com.shadowtech.chatroom.databinding.FragmentDashboardBinding;

import java.util.Date;


public class DashboardFragment extends Fragment {


    public DashboardFragment() {
        // Required empty public constructor
    }

FragmentDashboardBinding binding;
    ProgressDialog dialog;
    String[] toolbarname = {"Chats" , "Groups" , "Status" };
    String[] searchbarname = {"Search Chats" , "Search Groups" , "Search Status" };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(getLayoutInflater());
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Status Uploading .....");


        binding.vpDashboard.setAdapter(new vpAdapter(getChildFragmentManager() , getLifecycle()));




        binding.tbDashboard.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.vpDashboard.setCurrentItem(tab.getPosition());
                if(tab.isSelected())
                {
                    if(tab.getPosition() == 3)
                    {
                        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frMainActivityContainer , new ProfileFragment());
                        fragmentTransaction.commit();
                    }
                    else {
                        binding.txDashboardToolbar.setText(toolbarname[tab.getPosition()]);
                        binding.edsearchbox.setHint(searchbarname[tab.getPosition()]);
                    }

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        binding.vpDashboard.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.tbDashboard.selectTab(binding.tbDashboard.getTabAt(position));
            }
        });
        return binding.getRoot();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Activity.RESULT_OK)
        {
            if(data != null )
            {
                if(data.getData() != null)
                {
                    dialog.show();
                    FirebaseStorage storage = FirebaseStorage.getInstance();
                    Date date = new Date();
                    StorageReference reference = storage.getReference().child("Status").child(date.getTime() +"");
                    reference.putFile(data.getData()).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if(task.isSuccessful())
                            {
                                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        dialog.dismiss();
                                    }
                                });
                            }
                        }
                    });
                }
            }
        }
    }
}