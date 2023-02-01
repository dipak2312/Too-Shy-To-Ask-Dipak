package com.example.tooshytoask.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.Activity.Help.HelpActivity2;
import com.example.tooshytoask.Models.Help.data;
import com.example.tooshytoask.Models.HelpCategory;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class HelpCategoryAdapter extends RecyclerView.Adapter<HelpCategoryAdapter.ViewHolder> {

    Context context;
    ArrayList<com.example.tooshytoask.Models.Help.data>data;

    public HelpCategoryAdapter(Context context,ArrayList<data>data) {
        this.data = data;
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
        //holder.help_img.setImageDrawable(ContextCompat.getDrawable(context, helpCategories.get(position).getHelp_img()));
        holder.help_text.setText(data.get(position).getTitle());
        Glide.with(context).load(data.get(position).getImg()).into(holder.help_img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView help_img;
        TextView help_text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            help_img = itemView.findViewById(R.id.help_img);
            help_text = itemView.findViewById(R.id.help_text);

            help_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, HelpActivity2.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
            });
        }
    }
}
