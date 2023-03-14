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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.Activity.Blogs.DetailBlogActivity;
import com.example.tooshytoask.Models.relatedblogs;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class RelatedBlogAdapter extends RecyclerView.Adapter<RelatedBlogAdapter.ViewHolder> {
    Context context;
    ArrayList<relatedblogs> relatedblogs;

    public RelatedBlogAdapter(Context context, ArrayList<com.example.tooshytoask.Models.relatedblogs> relatedblogs) {
        this.context = context;
        this.relatedblogs = relatedblogs;
    }

    @NonNull
    @Override
    public RelatedBlogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_items_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RelatedBlogAdapter.ViewHolder holder, int position) {
        holder.blog_title.setText(relatedblogs.get(position).getBlog_title());
        Glide.with(context).load(relatedblogs.get(position).getBlog_img()).into(holder.blog_img);
    }

    @Override
    public int getItemCount() {
        return relatedblogs.size();
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

                    bundle.putString("blog_id",relatedblogs.get(getAdapterPosition()).getBlog_id());
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