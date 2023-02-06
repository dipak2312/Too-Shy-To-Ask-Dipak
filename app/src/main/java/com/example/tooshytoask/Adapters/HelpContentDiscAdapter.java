package com.example.tooshytoask.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.Models.Help.helpcontent;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class HelpContentDiscAdapter extends RecyclerView.Adapter<HelpContentDiscAdapter.ViewHolder> {
    ArrayList<helpcontent> helpcontent;
    Context context;

    public HelpContentDiscAdapter(ArrayList<helpcontent> helpcontent, Context context) {
        this.helpcontent = helpcontent;
        this.context = context;
    }

    @NonNull
    @Override
    public HelpContentDiscAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.help_disc_item_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HelpContentDiscAdapter.ViewHolder holder, int position) {
        holder.topic_title.setText(helpcontent.get(position).getHelpcontent_title());
        holder.description.setText(helpcontent.get(position).getHelpcontent_desc());

    }

    @Override
    public int getItemCount() {
        return helpcontent.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView topic_title,description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.description);
            topic_title = itemView.findViewById(R.id.topic_title);
        }
    }
}
