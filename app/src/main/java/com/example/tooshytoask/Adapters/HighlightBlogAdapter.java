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
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.InsightScreen.higlights;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.GuestLoginPopup;
import com.example.tooshytoask.Utils.OnBookmarkClicked;

import java.util.ArrayList;

public class HighlightBlogAdapter extends RecyclerView.Adapter<HighlightBlogAdapter.ViewHolder>{
    Context context;
    ArrayList<higlights>higlights;
    OnBookmarkClicked onBookmarkClicked;
    boolean like = true;
    String type = "blog";
    SPManager spManager;

    public HighlightBlogAdapter(Context context, ArrayList<higlights> higlights,
                                OnBookmarkClicked onBookmarkClicked, String type, SPManager spManager) {
        this.context = context;
        this.higlights = higlights;
        this.onBookmarkClicked = onBookmarkClicked;
        this.type = type;
        this.spManager = spManager;
    }


    @NonNull
    @Override
    public HighlightBlogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_items_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HighlightBlogAdapter.ViewHolder holder, int position) {

        holder.blog_title.setText(higlights.get(position).getBlog_title());
        Glide.with(context).load(higlights.get(position).getBlog_img()).into(holder.blog_img);

        if (spManager.getTstaguestLoginStatus().equals("false")) {
        if (higlights.get(position).getBlog_bookmarked().equals("1")){
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
        }
        holder.save_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (like) {
                    holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.saved_bookmark));
                    onBookmarkClicked.onBookmarkButtonClick(position,higlights.get(position).getBlog_id(), "save");
                    like = false;

                } else  {
                    holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.save));
                    onBookmarkClicked.onBookmarkButtonClick(position,higlights.get(position).getBlog_id(), "remove");
                    like = true;

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return higlights.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView blog_img, save_img, lock_img;
        TextView blog_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            blog_img = itemView.findViewById(R.id.blog_img);
            save_img = itemView.findViewById(R.id.save_img);
            blog_title = itemView.findViewById(R.id.blog_title);
            lock_img = itemView.findViewById(R.id.lock_img);

            blog_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (spManager.getTstaguestLoginStatus().equals("false")) {
                    Bundle bundle = new Bundle();

                    bundle.putString("blog_id",higlights.get(getAdapterPosition()).getBlog_id());
                    Intent intent = new Intent(context, DetailBlogActivity.class);
                    intent.putExtras(bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    }
                    else {
                        GuestLoginPopup.LogOut(context, spManager);
                    }
                }
            });
        }
    }
}
