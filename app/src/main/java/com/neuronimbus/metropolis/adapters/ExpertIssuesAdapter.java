package com.neuronimbus.metropolis.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.neuronimbus.metropolis.Models.AskExpert.data;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.onStoreHouseClick;

import java.util.ArrayList;

public class ExpertIssuesAdapter extends RecyclerView.Adapter<ExpertIssuesAdapter.ViewHolder> {
    Context context;
    ArrayList<data>data;
    onStoreHouseClick onStoreHouseClick;
    String text;

    public ExpertIssuesAdapter(Context context, ArrayList<com.neuronimbus.metropolis.Models.AskExpert.data> data, com.neuronimbus.metropolis.Utils.onStoreHouseClick onStoreHouseClick) {
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
        text = data.get(position).getTitle();
        holder.issues_txt.setText(Html.fromHtml(context.getResources().getString(R.string.i_have_que_related_to) + " " + text));
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
