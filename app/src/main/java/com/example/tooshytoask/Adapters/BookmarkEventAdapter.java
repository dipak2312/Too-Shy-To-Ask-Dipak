package com.example.tooshytoask.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.R;
import com.google.android.material.imageview.ShapeableImageView;

public class BookmarkEventAdapter extends RecyclerView.Adapter<BookmarkEventAdapter.ViewHolder> {

    @NonNull
    @Override
    public BookmarkEventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recently_blog_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkEventAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
            save_img.setVisibility(View.GONE);
            blog_title = itemView.findViewById(R.id.blog_title);
        }
    }
}
