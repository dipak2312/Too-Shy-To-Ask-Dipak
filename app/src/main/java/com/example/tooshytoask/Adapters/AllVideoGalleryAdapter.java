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
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.Activity.Blogs.DetailBlogActivity;
import com.example.tooshytoask.Activity.VideoGallery.VideoGallerySingleActivity;
import com.example.tooshytoask.Models.InsightScreen.video_gallery;
import com.example.tooshytoask.Models.insightvideo;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class AllVideoGalleryAdapter extends RecyclerView.Adapter<AllVideoGalleryAdapter.ViewHolder>{
    Context context;
    ArrayList<insightvideo>insightvideo;

    public AllVideoGalleryAdapter(Context context, ArrayList<insightvideo>insightvideo) {
        this.context = context;
        this.insightvideo = insightvideo;
    }

    @NonNull
    @Override
    public AllVideoGalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_video_gallery_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllVideoGalleryAdapter.ViewHolder holder, int position) {
        //Glide.with(context).load(video_gallery.get(position).get()).into(holder.blog_img);
        holder.blog_title.setText(insightvideo.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return insightvideo.size();
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
