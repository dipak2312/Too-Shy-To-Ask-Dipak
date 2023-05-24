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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.neuronimbus.metropolis.Utils.BookmarkClicked;
import com.neuronimbus.metropolis.activity.Blogs.DetailBlogActivity;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.InsightScreen.higlights;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.GuestLoginPopup;
import com.neuronimbus.metropolis.Utils.OnBookmarkClicked;

import java.util.ArrayList;

public class HighlightBlogAdapter extends RecyclerView.Adapter<HighlightBlogAdapter.ViewHolder>{
    Context context;
    ArrayList<higlights>higlights;
    com.neuronimbus.metropolis.Utils.BookmarkClicked BookmarkClicked;
    boolean like = true;
    SPManager spManager;

    public HighlightBlogAdapter(Context context, ArrayList<higlights> higlights,
                                BookmarkClicked BookmarkClicked , SPManager spManager) {
        this.context = context;
        this.higlights = higlights;
        this.BookmarkClicked = BookmarkClicked;
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
            holder.save_img.setVisibility(View.GONE);
        }
        holder.save_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (like) {
                    holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.saved_bookmark));
                    BookmarkClicked.BookmarkButtonClick(position,higlights.get(position).getBlog_id(), "save", "blog");
                    like = false;

                } else  {
                    holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.save));
                    BookmarkClicked.BookmarkButtonClick(position,higlights.get(position).getBlog_id(), "remove", "blog");
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
        CardView blog_card_view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            blog_img = itemView.findViewById(R.id.blog_img);
            save_img = itemView.findViewById(R.id.save_img);
            blog_title = itemView.findViewById(R.id.blog_title);
            lock_img = itemView.findViewById(R.id.lock_img);
            blog_card_view = itemView.findViewById(R.id.blog_card_view);

            blog_card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (spManager.getTstaguestLoginStatus().equals("false")) {
                        Bundle bundle = new Bundle();

                        bundle.putString("blog_id",higlights.get(getAdapterPosition()).getBlog_id());
                        Intent intent = new Intent(context, DetailBlogActivity.class);
                        intent.putExtras(bundle);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                    else {
                        GuestLoginPopup.LogOut(context, spManager);
                    }
                }
            });
        }
    }
}
