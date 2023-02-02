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
import com.example.tooshytoask.Models.InsightScreen.video_gallery;
import com.example.tooshytoask.Models.VideoGalleryItems;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class VideoGalleryAdapter extends RecyclerView.Adapter<VideoGalleryAdapter.ViewHolder>{
    Context context;
    ArrayList<video_gallery>video_gallery;

    public VideoGalleryAdapter(Context context, ArrayList<video_gallery>video_gallery) {
        this.context = context;
        this.video_gallery = video_gallery;
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

    }

    @Override
    public int getItemCount() {
        return video_gallery.size();
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
