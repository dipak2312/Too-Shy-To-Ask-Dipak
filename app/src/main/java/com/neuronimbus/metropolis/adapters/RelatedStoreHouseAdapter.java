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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.neuronimbus.metropolis.activity.InformationStoreHouse.InformationStoreHouseDetailActivity;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.OnBookmarkClicked;

import java.util.ArrayList;

public class RelatedStoreHouseAdapter extends RecyclerView.Adapter<RelatedStoreHouseAdapter.ViewHolder> {
    Context context;
    ArrayList<com.neuronimbus.metropolis.Models.relatedstorehouse>relatedstorehouse;
    OnBookmarkClicked onBookmarkClicked;
    boolean like = true;

    public RelatedStoreHouseAdapter(Context context, ArrayList<com.neuronimbus.metropolis.Models.relatedstorehouse> relatedstorehouse, OnBookmarkClicked onBookmarkClicked) {
        this.context = context;
        this.relatedstorehouse = relatedstorehouse;
        this.onBookmarkClicked = onBookmarkClicked;
    }


    @NonNull
    @Override
    public RelatedStoreHouseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_items_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RelatedStoreHouseAdapter.ViewHolder holder, int position) {
        holder.blog_title.setText(relatedstorehouse.get(position).getArticle_name());
        Glide.with(context).load(relatedstorehouse.get(position).getArticle_image()).into(holder.blog_img);

        if (relatedstorehouse.get(position).getBookmarked().equals("1")){
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
                    onBookmarkClicked.onBookmarkButtonClick(position,relatedstorehouse.get(position).getArticle_id(), "save");
                    like = false;

                } else  {
                    holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.save));
                    onBookmarkClicked.onBookmarkButtonClick(position,relatedstorehouse.get(position).getArticle_id(), "remove");
                    like = true;

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return relatedstorehouse.size();
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

                    bundle.putString("article_id",relatedstorehouse.get(getAdapterPosition()).getArticle_id());
                    Intent intent = new Intent(context, InformationStoreHouseDetailActivity.class);
                    intent.putExtras(bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
