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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.neuronimbus.metropolis.activity.Blogs.DetailBlogActivity;
import com.neuronimbus.metropolis.Models.insightevents;
import com.neuronimbus.metropolis.R;

import java.util.ArrayList;

public class AllRecentlyAddedAdapter extends RecyclerView.Adapter<AllRecentlyAddedAdapter.ViewHolder> {
    Context context;
    ArrayList<insightevents> insightevents;

    public AllRecentlyAddedAdapter(Context context, ArrayList<insightevents> insightevents) {
        this.context = context;
        this.insightevents = insightevents;
    }


    @NonNull
    @Override
    public AllRecentlyAddedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recently_blog_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllRecentlyAddedAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(insightevents.get(position).getBlog_img()).into(holder.blog_img);
        holder.blog_title.setText(insightevents.get(position).getEvent_title());
    }

    @Override
    public int getItemCount() {
        return insightevents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView blog_img, save_img;
        TextView blog_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            blog_img = itemView.findViewById(R.id.blog_img);
            save_img = itemView.findViewById(R.id.save_img);
            blog_title = itemView.findViewById(R.id.blog_title);

            blog_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();

                    bundle.putString("blog_id",insightevents.get(getAdapterPosition()).getEvent_id());
                    Intent intent = new Intent(context, DetailBlogActivity.class);
                    intent.putExtras(bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
