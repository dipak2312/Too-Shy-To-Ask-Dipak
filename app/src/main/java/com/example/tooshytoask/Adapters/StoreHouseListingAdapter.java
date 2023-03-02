package com.example.tooshytoask.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.R;
import com.google.android.material.imageview.ShapeableImageView;

public class StoreHouseListingAdapter extends RecyclerView.Adapter<StoreHouseListingAdapter.ViewHolder> {
    @NonNull
    @Override
    public StoreHouseListingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmark_storehouse_item_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreHouseListingAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView storehouse_items_img;
        TextView storehouse_item_txt;
        ImageView save_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            storehouse_items_img = itemView.findViewById(R.id.storehouse_items_img);
            storehouse_item_txt = itemView.findViewById(R.id.storehouse_item_txt);
            save_img = itemView.findViewById(R.id.save_img);
        }
    }
}
