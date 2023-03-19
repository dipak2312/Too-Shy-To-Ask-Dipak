package com.example.tooshytoask.adapters;

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
import com.example.tooshytoask.activity.VideoGallery.VideoGallerySingleActivity;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.insightvideo;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.OnBookmarkClicked;

import java.util.ArrayList;

public class AllVideoGalleryAdapter extends RecyclerView.Adapter<AllVideoGalleryAdapter.ViewHolder>{
    Context context;
    ArrayList<insightvideo>insightvideo;
    OnBookmarkClicked onBookmarkClicked;
    boolean like = true;
    SPManager spManager;

    public AllVideoGalleryAdapter(Context context, ArrayList<com.example.tooshytoask.Models.insightvideo> insightvideo,
                                  OnBookmarkClicked onBookmarkClicked, SPManager spManager) {
        this.context = context;
        this.insightvideo = insightvideo;
        this.onBookmarkClicked = onBookmarkClicked;
        this.spManager = spManager;
    }


    @NonNull
    @Override
    public AllVideoGalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_video_gallery_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllVideoGalleryAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(insightvideo.get(position).getCoverimage()).into(holder.blog_img);
        holder.blog_title.setText(insightvideo.get(position).getTitle());

        if (spManager.getTstaguestLoginStatus().equals("false")) {
        if (insightvideo.get(position).getBookmarked().equals("1")){
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
                    onBookmarkClicked.onBookmarkButtonClick(position,insightvideo.get(position).getId(), "save");
                    like = false;

                } else  {
                    holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.save));
                    onBookmarkClicked.onBookmarkButtonClick(position,insightvideo.get(position).getId(), "remove");
                    like = true;

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return insightvideo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView blog_img, save_img, play_video, lock_img;
        TextView blog_title;
        CardView video_play_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            blog_img = itemView.findViewById(R.id.blog_img);
            save_img = itemView.findViewById(R.id.save_img);
            play_video = itemView.findViewById(R.id.play_video);
            blog_title = itemView.findViewById(R.id.blog_title);
            video_play_btn = itemView.findViewById(R.id.video_play_btn);
            lock_img = itemView.findViewById(R.id.lock_img);

            video_play_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();

                    bundle.putString("video_link",insightvideo.get(getAdapterPosition()).getLink());
                    Intent intent = new Intent(context, VideoGallerySingleActivity.class);
                    intent.putExtras(bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
