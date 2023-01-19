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

import com.example.tooshytoask.Models.HighlightBlogItems;
import com.example.tooshytoask.Models.RecentlyBlogItems;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class HighlightBlogAdapter extends RecyclerView.Adapter<HighlightBlogAdapter.ViewHolder>{
    Context context;
    ArrayList<HighlightBlogItems>highlightBlogItem;

    public HighlightBlogAdapter(Context context, ArrayList<HighlightBlogItems>highlightBlogItem) {
        this.context = context;
        this.highlightBlogItem = highlightBlogItem;
    }

    @NonNull
    @Override
    public HighlightBlogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recently_blog_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HighlightBlogAdapter.ViewHolder holder, int position) {
        holder.blog_img.setImageDrawable(ContextCompat.getDrawable(context,highlightBlogItem.get(position).getBlog_img()));
        holder.save_img.setImageDrawable(ContextCompat.getDrawable(context,highlightBlogItem.get(position).getSave_img()));
        holder.blog_title.setText(highlightBlogItem.get(position).getBlog_title());

    }

    @Override
    public int getItemCount() {
        return highlightBlogItem.size();
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
