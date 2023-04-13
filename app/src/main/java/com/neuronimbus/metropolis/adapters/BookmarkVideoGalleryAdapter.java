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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.neuronimbus.metropolis.activity.VideoGallery.VideoGallerySingleActivity;
import com.neuronimbus.metropolis.R;

import java.util.ArrayList;

public class BookmarkVideoGalleryAdapter extends RecyclerView.Adapter<BookmarkVideoGalleryAdapter.ViewHolder> {
    Context context;
    ArrayList<com.neuronimbus.metropolis.Models.videobookmarks> videobookmarks;

    public BookmarkVideoGalleryAdapter(Context context, ArrayList<com.neuronimbus.metropolis.Models.videobookmarks> videobookmarks) {
        this.context = context;
        this.videobookmarks = videobookmarks;
    }

    @NonNull
    @Override
    public BookmarkVideoGalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_video_gallery_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkVideoGalleryAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(videobookmarks.get(position).getBookmark_img()).into(holder.blog_img);
        holder.blog_title.setText(videobookmarks.get(position).getBookmark_posttitle());
    }

    @Override
    public int getItemCount() {
        return videobookmarks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView blog_img, save_img, play_video;
        TextView blog_title;
        CardView video_play_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            blog_img = itemView.findViewById(R.id.blog_img);
            save_img = itemView.findViewById(R.id.save_img);
            save_img.setVisibility(View.GONE);
            play_video = itemView.findViewById(R.id.play_video);
            blog_title = itemView.findViewById(R.id.blog_title);
            video_play_btn = itemView.findViewById(R.id.video_play_btn);

            video_play_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();

                    bundle.putString("video_link",videobookmarks.get(getAdapterPosition()).getBookmark_imgvid());
                    bundle.putString("video_type",videobookmarks.get(getAdapterPosition()).getBookmark_videotype());
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
