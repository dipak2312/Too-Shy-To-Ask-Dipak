package com.example.tooshytoask.Adapters;

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
import com.example.tooshytoask.Models.articleblogs;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.OnBookmarkClicked;

import java.util.ArrayList;

public class AllBlogAdapter extends RecyclerView.Adapter<AllBlogAdapter.ViewHolder>{
    Context context;
    ArrayList<articleblogs>insightblogs;
    OnBookmarkClicked onBookmarkClicked;
    boolean like = true;

    public AllBlogAdapter(Context context, ArrayList<articleblogs> insightblogs, OnBookmarkClicked onBookmarkClicked) {
        this.context = context;
        this.insightblogs = insightblogs;
        this.onBookmarkClicked = onBookmarkClicked;
    }


    @NonNull
    @Override
    public AllBlogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recently_blog_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllBlogAdapter.ViewHolder holder, int position) {
        insightblogs.get(position).getBlog_category_name();
        Glide.with(context).load(insightblogs.get(position).getBlog_img()).into(holder.blog_img);
        holder.blog_title.setText(insightblogs.get(position).getBlog_title());

        if (insightblogs.get(position).getBlog_bookmarked().equals("1")){
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
                    onBookmarkClicked.onBookmarkButtonClick(position,insightblogs.get(position).getBlog_id(), "save");
                    like = false;

                } else  {
                    holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.save));
                    onBookmarkClicked.onBookmarkButtonClick(position,insightblogs.get(position).getBlog_id(), "remove");
                    like = true;

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return insightblogs.size();
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

                    bundle.putString("blog_id",insightblogs.get(getAdapterPosition()).getBlog_id());
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
