package com.example.tooshytoask.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.R;

public class AskExpertIssueAdapter extends RecyclerView.Adapter<AskExpertIssueAdapter.ViewHolder> {
    Context context;

    @NonNull
    @Override
    public AskExpertIssueAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ask_expert_issues_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AskExpertIssueAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rel_lay_id;
        TextView issues_txt, title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            issues_txt = itemView.findViewById(R.id.issues_txt);
            title = itemView.findViewById(R.id.title);
            rel_lay_id = itemView.findViewById(R.id.rel_lay_id);
        }
    }
}
