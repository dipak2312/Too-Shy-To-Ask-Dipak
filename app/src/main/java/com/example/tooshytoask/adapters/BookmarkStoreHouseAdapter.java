package com.example.tooshytoask.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmark_storehouse_item_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkStoreHouseAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(infostorehousebookmark.get(position).getBookmark_imgvid()).into(holder.storehouse_items_img);
        holder.storehouse_item_txt.setText(infostorehousebookmark.get(position).getBookmark_posttitle());
    }

    @Override
    public int getItemCount() {
        return infostorehousebookmark.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout storehouse_lay;
        ShapeableImageView storehouse_items_img;
        TextView storehouse_item_txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            storehouse_lay = itemView.findViewById(R.id.storehouse_lay);
            storehouse_items_img = itemView.findViewById(R.id.storehouse_items_img);
            storehouse_item_txt = itemView.findViewById(R.id.storehouse_item_txt);

            storehouse_lay.setOnClickListener(new View.OnClickListener() {
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
