package com.example.tooshytoask.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.Models.StoryDetails;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class StoryDetailAdapter extends RecyclerView.Adapter<StoryDetailAdapter.ViewHolder> {
    Context context;
    ArrayList<StoryDetails> storyDetails;

    public StoryDetailAdapter(Context context, ArrayList<StoryDetails> storyDetails) {
        this.context = context;
        this.storyDetails = storyDetails;
    }

    @NonNull
    @Override
    public StoryDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.story_display_item_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryDetailAdapter.ViewHolder holder, int position) {

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
