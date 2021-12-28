package com.shadowtech.chatroom.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shadowtech.chatroom.Adapter.TopStoryRecyclerAdapter;
import com.shadowtech.chatroom.Model.UserStatus;
import com.shadowtech.chatroom.R;
import com.shadowtech.chatroom.databinding.FragmentChatBinding;

import java.util.ArrayList;

public class ChatFragment extends Fragment {



    public ChatFragment() {
        // Required empty public constructor
    }
    FragmentChatBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChatBinding.inflate(getLayoutInflater());




        return binding.getRoot();
    }
}