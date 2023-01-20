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

import com.example.tooshytoask.Models.EventBlogItems;
import com.example.tooshytoask.Models.HighlightBlogItems;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class EventBlogAdapter extends RecyclerView.Adapter<EventBlogAdapter.ViewHolder>{
    Context context;
    ArrayList<EventBlogItems>eventBlogItems;

    public EventBlogAdapter(Context context, ArrayList<EventBlogItems>eventBlogItems) {
        this.context = context;
        this.eventBlogItems = eventBlogItems;
    }

    @NonNull
    @Override
    public EventBlogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_items_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventBlogAdapter.ViewHolder holder, int position) {
        holder.blog_img.setImageDrawable(ContextCompat.getDrawable(context,eventBlogItems.get(position).getBlog_img()));
        holder.save_img.setImageDrawable(ContextCompat.getDrawable(context,eventBlogItems.get(position).getSave_img()));
        holder.blog_title.setText(eventBlogItems.get(position).getBlog_title());

    }

    @Override
    public int getItemCount() {
        return eventBlogItems.size();
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
