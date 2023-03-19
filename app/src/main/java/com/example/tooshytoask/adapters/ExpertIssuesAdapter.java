package com.example.tooshytoask.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.Models.AskExpert.data;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.onStoreHouseClick;

import java.util.ArrayList;

public class ExpertIssuesAdapter extends RecyclerView.Adapter<ExpertIssuesAdapter.ViewHolder> {
    Context context;
    ArrayList<data>data;
    onStoreHouseClick onStoreHouseClick;

    public ExpertIssuesAdapter(Context context, ArrayList<com.example.tooshytoask.Models.AskExpert.data> data, com.example.tooshytoask.Utils.onStoreHouseClick onStoreHouseClick) {
        this.context = context;
        this.data = data;
        this.onStoreHouseClick = onStoreHouseClick;
    }


    @NonNull
    @Override
    public ExpertIssuesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ask_expert_issues_items, parent,  false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpertIssuesAdapter.ViewHolder holder, int position) {
        holder.issues_txt.setText(Html.fromHtml(data.get(position).getTitle()));

        holder.rel_lay_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStoreHouseClick.OnStoreHouseButtonClick(position,data.get(position).getTitle_id(),
                        data.get(position).getTitle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
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
