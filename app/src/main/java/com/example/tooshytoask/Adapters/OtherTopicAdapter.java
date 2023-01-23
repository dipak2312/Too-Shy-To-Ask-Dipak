package com.example.tooshytoask.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.Models.OtherTopicItem;

import java.util.ArrayList;

public class OtherTopicAdapter extends RecyclerView.Adapter<OtherTopicAdapter.ViewHolder>{
    Context context;
    ArrayList<OtherTopicItem> otherTopicItems;


    @NonNull
    @Override
    public OtherTopicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull OtherTopicAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
