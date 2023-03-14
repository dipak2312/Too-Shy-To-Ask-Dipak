package com.example.tooshytoask.Adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.Models.insightblogcategories;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class ExpertIssuesAdapter extends RecyclerView.Adapter<ExpertIssuesAdapter.ViewHolder> {
    Context context;
    ArrayList<com.example.tooshytoask.Models.insightblogcategories> insightblogcategories;

    public ExpertIssuesAdapter(Context context, ArrayList<com.example.tooshytoask.Models.insightblogcategories> insightblogcategories) {
        this.context = context;
        this.insightblogcategories = insightblogcategories;
    }

    @NonNull
    @Override
    public ExpertIssuesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ask_expert_issues_items, parent,  false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpertIssuesAdapter.ViewHolder holder, int position) {
        holder.issues_txt.setText(Html.fromHtml(insightblogcategories.get(position).getCategory_title()));
    }

    @Override
    public int getItemCount() {
        return insightblogcategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rel_lay_id;
        TextView issues_txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            issues_txt = itemView.findViewById(R.id.issues_txt);
            rel_lay_id = itemView.findViewById(R.id.rel_lay_id);
        }
    }
}
