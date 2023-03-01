package com.example.tooshytoask.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.R;

public class ExpertIssuesAdapter extends RecyclerView.Adapter<ExpertIssuesAdapter.ViewHolder> {
    Context context;


    @NonNull
    @Override
    public ExpertIssuesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ask_expert_issues_items, parent,  false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpertIssuesAdapter.ViewHolder holder, int position) {

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
