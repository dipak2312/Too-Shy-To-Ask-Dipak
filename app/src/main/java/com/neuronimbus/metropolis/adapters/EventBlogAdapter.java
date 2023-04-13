package com.neuronimbus.metropolis.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Utils.BookmarkClicked;
import com.neuronimbus.metropolis.activity.Blogs.DetailEventActivity;
import com.neuronimbus.metropolis.Models.InsightScreen.events;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.OnBookmarkClicked;

import java.util.ArrayList;

public class EventBlogAdapter extends RecyclerView.Adapter<EventBlogAdapter.ViewHolder>{
    Context context;
    ArrayList<events>events;
    BookmarkClicked BookmarkClicked;
    SPManager spManager;
    boolean like = true;

    public EventBlogAdapter(Context context, ArrayList<events> events, BookmarkClicked bookmarkClicked, SPManager spManager) {
        this.context = context;
        this.events = events;
        BookmarkClicked = bookmarkClicked;
        this.spManager = spManager;
    }


    @NonNull
    @Override
    public EventBlogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_items_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventBlogAdapter.ViewHolder holder, int position) {
        holder.blog_title.setText(Html.fromHtml(events.get(position).getEvent_title()));
        Glide.with(context).load(events.get(position).getBlog_img()).into(holder.blog_img);

        if (spManager.getTstaguestLoginStatus().equals("false")) {
        if (events.get(position).getBlog_bookmarked().equals("1")){
            holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.saved_bookmark));
            like = false;
        }
        else  {
            holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.save));
            like = true;

        }
        }
        else {
            holder.lock_img.setVisibility(View.VISIBLE);
            holder.save_img.setVisibility(View.GONE);
        }
        holder.save_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (like) {
                    holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.saved_bookmark));
                    BookmarkClicked.BookmarkButtonClick(position,events.get(position).getEvent_id(), "save", "event");
                    like = false;

                } else  {
                    holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.save));
                    BookmarkClicked.BookmarkButtonClick(position,events.get(position).getEvent_id(), "remove", "event");
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
        ImageView blog_img, save_img, lock_img;
        TextView blog_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lock_img = itemView.findViewById(R.id.lock_img);
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
