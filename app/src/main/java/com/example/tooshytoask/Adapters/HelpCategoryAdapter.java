package com.example.tooshytoask.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.Models.HelpCategory;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class HelpCategoryAdapter extends RecyclerView.Adapter<HelpCategoryAdapter.ViewHolder> {
    ArrayList<HelpCategory> helpCategories;
    Context context;

    public HelpCategoryAdapter(Context context, ArrayList<HelpCategory>helpCategories) {
        this.helpCategories = helpCategories;
        this.context = context;
    }

    @NonNull
    @Override
    public HelpCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.help_category,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HelpCategoryAdapter.ViewHolder holder, int position) {
        holder.help_img.setImageDrawable(ContextCompat.getDrawable(context, helpCategories.get(position).getHelp_img()));
        holder.help_text.setText(helpCategories.get(position).getHelp_text());
    }

    @Override
    public int getItemCount() {
        return helpCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView help_img;
        TextView help_text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            help_img = itemView.findViewById(R.id.help_img);
            help_text = itemView.findViewById(R.id.help_text);
        }
    }
}
