package com.shadowtech.chatroom.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shadowtech.chatroom.Model.UserStatus;
import com.shadowtech.chatroom.R;
import com.shadowtech.chatroom.databinding.ChatStorySampleBinding;

import java.util.ArrayList;

public class TopStoryRecyclerAdapter extends RecyclerView.Adapter<TopStoryRecyclerAdapter.viewHolder> {
    Context context;
    ArrayList<UserStatus> statuses;

    public TopStoryRecyclerAdapter(Context context, ArrayList<UserStatus> statuses) {
        this.context = context;
        this.statuses = statuses;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_story_sample , parent , false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return statuses.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        ChatStorySampleBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ChatStorySampleBinding.bind(itemView);
        }
    }
}
