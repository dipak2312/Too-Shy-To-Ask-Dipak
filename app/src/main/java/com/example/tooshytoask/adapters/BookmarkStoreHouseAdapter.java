package com.example.tooshytoask.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.activity.InformationStoreHouse.InformationStoreHouseDetailActivity;
import com.example.tooshytoask.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class BookmarkStoreHouseAdapter extends RecyclerView.Adapter<BookmarkStoreHouseAdapter.ViewHolder>{
    Context context;
    ArrayList<com.example.tooshytoask.Models.infostorehousebookmark>infostorehousebookmark;

    public BookmarkStoreHouseAdapter(Context context, ArrayList<com.example.tooshytoask.Models.infostorehousebookmark> infostorehousebookmark) {
        this.context = context;
        this.infostorehousebookmark = infostorehousebookmark;
    }


    @NonNull
    @Override
    public BookmarkStoreHouseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recently_blog_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkStoreHouseAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(infostorehousebookmark.get(position).getBookmark_img()).into(holder.blog_img);
        holder.blog_title.setText(infostorehousebookmark.get(position).getBookmark_posttitle());
    }

    @Override
    public int getItemCount() {
        return infostorehousebookmark.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView card_view;
        ShapeableImageView blog_img;
        ImageView save_img;
        TextView blog_title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            card_view = itemView.findViewById(R.id.card_view);
            blog_img = itemView.findViewById(R.id.blog_img);
            save_img = itemView.findViewById(R.id.save_img);
            blog_title = itemView.findViewById(R.id.blog_title);

            card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();

                    bundle.putString("article_id", infostorehousebookmark.get(getAdapterPosition()).getBookmark_postid());
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
