package com.example.tooshytoask.Adapters;

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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.Activity.InformationStoreHouse.InformationStoreHouseDetailActivity;
import com.example.tooshytoask.Activity.InformationStoreHouse.InformationStorehouseActivity;
import com.example.tooshytoask.Models.StoreHouse.CategoryData.data;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.OnBookmarkClicked;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class StoreHouseListingAdapter extends RecyclerView.Adapter<StoreHouseListingAdapter.ViewHolder> {
    Context context;
    ArrayList<com.example.tooshytoask.Models.StoreHouse.CategoryData.data>data;
    OnBookmarkClicked onBookmarkClicked;
    boolean like = true;

    public StoreHouseListingAdapter(Context context, ArrayList<com.example.tooshytoask.Models.StoreHouse.CategoryData.data> data, OnBookmarkClicked onBookmarkClicked) {
        this.context = context;
        this.data = data;
        this.onBookmarkClicked = onBookmarkClicked;

    }


    @NonNull
    @Override
    public StoreHouseListingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmark_storehouse_item_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreHouseListingAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(data.get(position).getArticle_image()).into(holder.storehouse_items_img);
        holder.storehouse_item_txt.setText(data.get(position).getArticle_name());

        if (data.get(position).getBookmarked().equals("1")){
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
                    onBookmarkClicked.onBookmarkButtonClick(position,data.get(position).getArticle_id(), "save");
                    like = false;

                } else  {
                    holder.save_img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.save));
                    onBookmarkClicked.onBookmarkButtonClick(position,data.get(position).getArticle_id(), "remove");
                    like = true;

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView storehouse_items_img;
        TextView storehouse_item_txt;
        ImageView save_img;
        LinearLayout storehouse_lay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            storehouse_items_img = itemView.findViewById(R.id.storehouse_items_img);
            storehouse_item_txt = itemView.findViewById(R.id.storehouse_item_txt);
            save_img = itemView.findViewById(R.id.save_img);
            storehouse_lay = itemView.findViewById(R.id.storehouse_lay);

            storehouse_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();

                    bundle.putString("article_id", data.get(getAdapterPosition()).getArticle_id());
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
