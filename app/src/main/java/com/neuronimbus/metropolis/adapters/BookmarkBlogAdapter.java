package com.neuronimbus.metropolis.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.neuronimbus.metropolis.activity.Blogs.DetailBlogActivity;
import com.neuronimbus.metropolis.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class BookmarkBlogAdapter extends RecyclerView.Adapter<BookmarkBlogAdapter.ViewHolder>{
    Context context;
    ArrayList<com.neuronimbus.metropolis.Models.blog_bookmark> blog_bookmark;

    public BookmarkBlogAdapter(Context context, ArrayList<com.neuronimbus.metropolis.Models.blog_bookmark> blog_bookmark) {
        this.context = context;
        this.blog_bookmark = blog_bookmark;
    }

    @NonNull
    @Override
    public BookmarkBlogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recently_blog_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkBlogAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(blog_bookmark.get(position).getBookmark_imgvid()).into(holder.blog_img);
        holder.blog_title.setText(blog_bookmark.get(position).getBookmark_posttitle());

    }

    @Override
    public int getItemCount() {
        return blog_bookmark.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView card_view;
        ShapeableImageView blog_img;
        ImageView save_img;
        TextView blog_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            card_view = itemView.findViewById(R.id.card_view);
            blog_img = itemView.findViewById(R.id.blog_img);
            save_img = itemView.findViewById(R.id.save_img);
            save_img.setVisibility(View.GONE);
            blog_title = itemView.findViewById(R.id.blog_title);

            blog_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();

                    bundle.putString("blog_id",blog_bookmark.get(getAdapterPosition()).getBookmark_postid());
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
