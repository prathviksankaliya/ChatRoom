package com.shadowtech.chatroom.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.shadowtech.chatroom.Fragments.ChatFragment;
import com.shadowtech.chatroom.Fragments.GroupFragment;
import com.shadowtech.chatroom.Fragments.ProfileFragment;
import com.shadowtech.chatroom.Fragments.StatusFragment;

public class vpAdapter extends FragmentStateAdapter {


    public vpAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {

            case 1:
               return new GroupFragment();

            case 2:
                return new StatusFragment();

            case 3:
                return new ProfileFragment();
            default:
                return new ChatFragment();
        }
    }


    @Override
    public int getItemCount() {
        return 4;
    }

}
