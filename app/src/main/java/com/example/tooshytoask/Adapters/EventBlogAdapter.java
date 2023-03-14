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
import com.example.tooshytoask.Activity.Blogs.DetailEventActivity;
import com.example.tooshytoask.Activity.InformationStoreHouse.InformationStoreHouseDetailActivity;
import com.example.tooshytoask.Models.InsightScreen.events;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.OnBookmarkClicked;

import java.util.ArrayList;

public class EventBlogAdapter extends RecyclerView.Adapter<EventBlogAdapter.ViewHolder>{
    Context context;
    ArrayList<events>events;
    OnBookmarkClicked onBookmarkClicked;
    boolean like = true;
    String type;

    public EventBlogAdapter(Context context, ArrayList<com.example.tooshytoask.Models.InsightScreen.events> events, OnBookmarkClicked onBookmarkClicked, String type) {
        this.context = context;
        this.events = events;
        this.onBookmarkClicked = onBookmarkClicked;
        this.type = type;
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

        if (events.get(position).getBlog_bookmarked().equals("1")){
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
                    onBookmarkClicked.onBookmarkButtonClick(position,events.get(position).getEvent_id(), "save");
                    like = false;

                } else  {
                    holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.save));
                    onBookmarkClicked.onBookmarkButtonClick(position,events.get(position).getEvent_id(), "remove");
                    like = true;

                }

            }
        });

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

            blog_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();

                    bundle.putString("blog_id",events.get(getAdapterPosition()).getEvent_id());
                    Intent intent = new Intent(context, DetailEventActivity.class);
                    intent.putExtras(bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
