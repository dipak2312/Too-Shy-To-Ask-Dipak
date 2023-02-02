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

import com.bumptech.glide.Glide;
import com.example.tooshytoask.Models.Blogs;
import com.example.tooshytoask.Models.RecentlyBlogItems;
import com.example.tooshytoask.Models.RecommendedBlogItems;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class RecentlyBlogAdapter extends RecyclerView.Adapter<RecentlyBlogAdapter.ViewHolder>{
    Context context;
    ArrayList<com.example.tooshytoask.Models.Blogs> Blogs;

    public RecentlyBlogAdapter(Context context,  ArrayList<Blogs> Blogs) {
        this.context = context;
        this.Blogs = Blogs;
    }

    @NonNull
    @Override
    public RecentlyBlogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_items_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentlyBlogAdapter.ViewHolder holder, int position) {
        holder.blog_title.setText(Blogs.get(position).getBlog_title());
        Glide.with(context).load(Blogs.get(position).getBlog_img()).into(holder.blog_img);


    }

    @Override
    public int getItemCount() {
        return Blogs.size();
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
