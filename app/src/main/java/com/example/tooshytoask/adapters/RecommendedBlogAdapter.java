package com.example.tooshytoask.adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.activity.Blogs.DetailBlogActivity;
import com.example.tooshytoask.Models.RecommendedBlogs;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.OnBookmarkClicked;

import java.util.ArrayList;


public class RecommendedBlogAdapter extends RecyclerView.Adapter<RecommendedBlogAdapter.ViewHolder>{
    Context context;
    ArrayList<RecommendedBlogs> RecommendedBlogs;
    OnBookmarkClicked onBookmarkClicked;
    boolean like = true;

    public RecommendedBlogAdapter(Context context, ArrayList<RecommendedBlogs> recommendedBlogs,
                                  OnBookmarkClicked onBookmarkClicked) {
        this.context = context;
        RecommendedBlogs = recommendedBlogs;
        this.onBookmarkClicked = onBookmarkClicked;
    }


    @NonNull
    @Override
    public RecommendedBlogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_blog_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position ) {
        holder.blog_title.setText(RecommendedBlogs.get(position).getBlog_title());
        Glide.with(context).load(RecommendedBlogs.get(position).getBlog_img()).into(holder.blog_img);


        if (RecommendedBlogs.get(position).getBlog_boomarked().equals("1")){
            holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.saved_bookmark));
            like = false;
        }
        else  {
            holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.save));
            like = true;

        }

        holder.save_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (like) {
                    holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.saved_bookmark));
                    onBookmarkClicked.onBookmarkButtonClick(position,RecommendedBlogs.get(position).getBlog_id(), "save");
                    like = false;

                } else  {
                    holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.save));
                    onBookmarkClicked.onBookmarkButtonClick(position,RecommendedBlogs.get(position).getBlog_id(), "remove");
                    like = true;

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return RecommendedBlogs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView blog_img, save_img;
        TextView blog_title;
        RelativeLayout blur_rel_lay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            blog_img = itemView.findViewById(R.id.blog_img);
            save_img = itemView.findViewById(R.id.save_img);
            blog_title = itemView.findViewById(R.id.blog_title);
            blur_rel_lay = itemView.findViewById(R.id.blur_rel_lay);


            blog_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();

                    bundle.putString("blog_id",RecommendedBlogs.get(getAdapterPosition()).getBlog_id());
                    Intent intent = new Intent(context, DetailBlogActivity.class);
                    intent.putExtras(bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }

}
