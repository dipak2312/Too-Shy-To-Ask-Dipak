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

import com.example.tooshytoask.Models.BlogItems;
import com.example.tooshytoask.Models.RecommendedBlogItems;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class RecommendedBlogAdapter extends RecyclerView.Adapter<RecommendedBlogAdapter.ViewHolder>{
    Context context;
    ArrayList<RecommendedBlogItems>recommendedBlogItems;

    public RecommendedBlogAdapter(Context context,  ArrayList<RecommendedBlogItems>recommendedBlogItems) {
        this.context = context;
        this.recommendedBlogItems = recommendedBlogItems;
    }

    @NonNull
    @Override
    public RecommendedBlogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_blog_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedBlogAdapter.ViewHolder holder, int position) {
        holder.blog_img.setImageDrawable(ContextCompat.getDrawable(context,recommendedBlogItems.get(position).getBlog_img()));
        holder.save_img.setImageDrawable(ContextCompat.getDrawable(context,recommendedBlogItems.get(position).getSave_img()));
        holder.blog_title.setText(recommendedBlogItems.get(position).getBlog_title());

    }

    @Override
    public int getItemCount() {
        return recommendedBlogItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView blog_img, save_img;
        TextView blog_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            blog_img = itemView.findViewById(R.id.blog_img);
            save_img = itemView.findViewById(R.id.save_img);
            blog_title = itemView.findViewById(R.id.blog_title);
        }
    }
}
