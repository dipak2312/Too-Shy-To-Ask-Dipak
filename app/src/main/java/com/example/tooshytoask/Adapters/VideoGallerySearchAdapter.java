package com.example.tooshytoask.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.Models.insightvideo;
import com.example.tooshytoask.Models.video_search;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class VideoGallerySearchAdapter extends RecyclerView.Adapter<VideoGallerySearchAdapter.ViewHolder>{
    Context context;
    ArrayList<video_search>Allvideo_search;

    public VideoGallerySearchAdapter(Context context, ArrayList<video_search> allvideo_search) {
        this.context = context;
        Allvideo_search = allvideo_search;
    }


    @NonNull
    @Override
    public VideoGallerySearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_video_gallery_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoGallerySearchAdapter.ViewHolder holder, int position) {
        //Glide.with(context).load(video_gallery.get(position).get()).into(holder.blog_img);
        holder.blog_title.setText(Allvideo_search.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return Allvideo_search.size();
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
