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
import com.example.tooshytoask.Models.HighlightBlogItems;
import com.example.tooshytoask.Models.InsightScreen.events;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class EventBlogAdapter extends RecyclerView.Adapter<EventBlogAdapter.ViewHolder>{
    Context context;
    ArrayList<events>events;

    public EventBlogAdapter(Context context, ArrayList<events>events) {
        this.context = context;
        this.events = events;
    }

    @NonNull
    @Override
    public EventBlogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_items_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventBlogAdapter.ViewHolder holder, int position) {
        holder.blog_title.setText(events.get(position).getEvent_title());
        Glide.with(context).load(events.get(position).getBlog_img()).into(holder.blog_img);

    }

    @Override
    public int getItemCount() {
        return events.size();
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
