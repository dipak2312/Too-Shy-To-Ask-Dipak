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
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.Activity.Blogs.DetailEventActivity;
import com.example.tooshytoask.Activity.VideoGallery.VideoGallerySingleActivity;
import com.example.tooshytoask.Models.InsightScreen.video_gallery;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.OnBookmarkClicked;

import java.util.ArrayList;

public class VideoGalleryAdapter extends RecyclerView.Adapter<VideoGalleryAdapter.ViewHolder>{
    Context context;
    ArrayList<video_gallery>video_gallery;
    OnBookmarkClicked onBookmarkClicked;
    boolean like = true;
    String type = "video";
    String link ="";

    public VideoGalleryAdapter(Context context, ArrayList<com.example.tooshytoask.Models.InsightScreen.video_gallery> video_gallery, OnBookmarkClicked onBookmarkClicked, String type) {
        this.context = context;
        this.video_gallery = video_gallery;
        this.onBookmarkClicked = onBookmarkClicked;
        this.type = type;
    }


    @NonNull
    @Override
    public VideoGalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_gallery_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoGalleryAdapter.ViewHolder holder, int position) {
        //Glide.with(context).load(video_gallery.get(position).get()).into(holder.blog_img);
        holder.blog_title.setText(video_gallery.get(position).getTitle());
        link = video_gallery.get(position).getLink();
        holder.save_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (like) {
                    holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.saved_bookmark));
                    onBookmarkClicked.onBookmarkButtonClick(position,video_gallery.get(position).getId());
                    like = false;

                } else  {
                    holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.save));
                    onBookmarkClicked.onBookmarkButtonClick(position,video_gallery.get(position).getId());
                    like = true;

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return video_gallery.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView blog_img, save_img, play_video;
        TextView blog_title;
        CardView video_gallery_view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            blog_img = itemView.findViewById(R.id.blog_img);
            save_img = itemView.findViewById(R.id.save_img);
            play_video = itemView.findViewById(R.id.play_video);
            blog_title = itemView.findViewById(R.id.blog_title);
            video_gallery_view = itemView.findViewById(R.id.video_gallery_view);

            video_gallery_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();

                    bundle.putString("video_link",link);
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
