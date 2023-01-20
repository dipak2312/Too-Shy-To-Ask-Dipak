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
import com.example.tooshytoask.Models.VideoGalleryItems;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class VideoGalleryAdapter extends RecyclerView.Adapter<VideoGalleryAdapter.ViewHolder>{
    Context context;
    ArrayList<VideoGalleryItems>videoGalleryItems;

    public VideoGalleryAdapter(Context context, ArrayList<VideoGalleryItems>videoGalleryItems) {
        this.context = context;
        this.videoGalleryItems = videoGalleryItems;
    }

    @NonNull
    @Override
    public VideoGalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_gallery_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoGalleryAdapter.ViewHolder holder, int position) {
        holder.blog_img.setImageDrawable(ContextCompat.getDrawable(context,videoGalleryItems.get(position).getBlog_img()));
        holder.save_img.setImageDrawable(ContextCompat.getDrawable(context,videoGalleryItems.get(position).getSave_img()));
        holder.play_video.setImageDrawable(ContextCompat.getDrawable(context,videoGalleryItems.get(position).getPlay_video()));
        holder.blog_title.setText(videoGalleryItems.get(position).getBlog_title());

    }

    @Override
    public int getItemCount() {
        return videoGalleryItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView blog_img, save_img, play_video;
        TextView blog_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            blog_img = itemView.findViewById(R.id.blog_img);
            save_img = itemView.findViewById(R.id.save_img);
            play_video = itemView.findViewById(R.id.play_video);
            blog_title = itemView.findViewById(R.id.blog_title);
        }
    }
}
