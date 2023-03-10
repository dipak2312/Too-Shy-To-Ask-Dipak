package com.example.tooshytoask.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.Activity.Blogs.DetailBlogActivity;
import com.example.tooshytoask.Models.RecommendedBlogs;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.OnBookmarkClicked;
import com.example.tooshytoask.Utils.OnClickListner;

import java.util.ArrayList;

public class RecommendedBlogAdapter extends RecyclerView.Adapter<RecommendedBlogAdapter.ViewHolder>{
    Context context;
    ArrayList<RecommendedBlogs> RecommendedBlogs;
    OnBookmarkClicked onBookmarkClicked;
    boolean like = true;

    public RecommendedBlogAdapter(Context context, ArrayList<com.example.tooshytoask.Models.RecommendedBlogs> recommendedBlogs, OnBookmarkClicked onBookmarkClicked) {
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
    public void onBindViewHolder(@NonNull RecommendedBlogAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position ) {
        holder.blog_title.setText(RecommendedBlogs.get(position).getBlog_title());
        Glide.with(context).load(RecommendedBlogs.get(position).getBlog_img()).into(holder.blog_img);


        holder.save_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (like) {
                    holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.saved_bookmark));
                    onBookmarkClicked.onBookmarkButtonClick(position,RecommendedBlogs.get(position).getBlog_id());
                    like = false;

                } else  {
                    holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.save));
                    onBookmarkClicked.onBookmarkButtonClick(position,RecommendedBlogs.get(position).getBlog_id());
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            blog_img = itemView.findViewById(R.id.blog_img);
            save_img = itemView.findViewById(R.id.save_img);
            blog_title = itemView.findViewById(R.id.blog_title);


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
