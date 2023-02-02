package com.example.tooshytoask.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.Models.RecommendedBlogs;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class RecommendedBlogAdapter extends RecyclerView.Adapter<RecommendedBlogAdapter.ViewHolder>{
    Context context;
    ArrayList<RecommendedBlogs> RecommendedBlogs;

    public RecommendedBlogAdapter(Context context,  ArrayList<RecommendedBlogs> RecommendedBlogs) {
        this.context = context;
        this.RecommendedBlogs = RecommendedBlogs;
    }

    @NonNull
    @Override
    public RecommendedBlogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_blog_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedBlogAdapter.ViewHolder holder, int position) {
        //holder.blog_img.setImageDrawable(ContextCompat.getDrawable(context,RecommendedBlogs.get(position).getBlog_img()));
        //holder.save_img.setImageDrawable(ContextCompat.getDrawable(context,RecommendedBlogs.get(position).getSave_img()));
        holder.blog_title.setText(RecommendedBlogs.get(position).getBlog_title());
        Glide.with(context).load(RecommendedBlogs.get(position).getBlog_img()).into(holder.blog_img);

    }

    @Override
    public int getItemCount() {
        return RecommendedBlogs.size();
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
