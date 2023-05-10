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
import com.neuronimbus.metropolis.Models.video_search;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.OnBookmarkClicked;
import com.neuronimbus.metropolis.activity.VideoGallery.VideoGallerySingleActivity;

import java.util.ArrayList;

public class VideoGallerySearchAdapter extends RecyclerView.Adapter<VideoGallerySearchAdapter.ViewHolder>{
    Context context;
    ArrayList<video_search>Allvideo_search;
    OnBookmarkClicked onBookmarkClicked;
    boolean like;
    String type ;

    public VideoGallerySearchAdapter(Context context, ArrayList<video_search> allvideo_search, OnBookmarkClicked onBookmarkClicked, String type) {
        this.context = context;
        Allvideo_search = allvideo_search;
        this.onBookmarkClicked = onBookmarkClicked;
        this.type = type;
    }


    @NonNull
    @Override
    public VideoGallerySearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_video_gallery_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoGallerySearchAdapter.ViewHolder holder, int position) {
        holder.blog_title.setText(Allvideo_search.get(position).getTitle());
        Glide.with(context).load(Allvideo_search.get(position).getCoverimage()).into(holder.blog_img);

        if (Allvideo_search.get(position).getBookmarked().equals("1")){
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
                    onBookmarkClicked.onBookmarkButtonClick(position,Allvideo_search.get(position).getId(), "save");
                    like = false;

                } else  {
                    holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.save));
                    onBookmarkClicked.onBookmarkButtonClick(position,Allvideo_search.get(position).getId(), "remove");
                    like = true;

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return Allvideo_search.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView blog_img, save_img, play_video;
        TextView blog_title;
        CardView video_play_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            blog_img = itemView.findViewById(R.id.blog_img);
            save_img = itemView.findViewById(R.id.save_img);
            play_video = itemView.findViewById(R.id.play_video);
            blog_title = itemView.findViewById(R.id.blog_title);
            video_play_btn = itemView.findViewById(R.id.video_play_btn);

            video_play_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();

                    bundle.putString("video_link",Allvideo_search.get(getAdapterPosition()).getLink());
                    bundle.putString("video_type",Allvideo_search.get(getAdapterPosition()).getVideo_type());
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
