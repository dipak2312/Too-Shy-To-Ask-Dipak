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
import com.example.tooshytoask.Models.EventBlogItems;
import com.example.tooshytoask.Models.InsightScreen.new_blogs;
import com.example.tooshytoask.Models.JustAddedBlogItems;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class JustAddedBlogAdapter extends RecyclerView.Adapter<JustAddedBlogAdapter.ViewHolder>{
    Context context;
    ArrayList<new_blogs>new_blogs;

    public JustAddedBlogAdapter(Context context, ArrayList<new_blogs>new_blogs) {
        this.context = context;
        this.new_blogs = new_blogs;
    }

    @NonNull
    @Override
    public JustAddedBlogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_items_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JustAddedBlogAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(new_blogs.get(position).getBlog_img()).into(holder.blog_img);
        holder.blog_title.setText(new_blogs.get(position).getBlog_title());

    }

    @Override
    public int getItemCount() {
        return new_blogs.size();
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
