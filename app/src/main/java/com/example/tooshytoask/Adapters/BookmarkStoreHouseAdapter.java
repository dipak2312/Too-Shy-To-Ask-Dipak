package com.example.tooshytoask.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.Models.OtherTopicItem;
import com.example.tooshytoask.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class BookmarkStoreHouseAdapter extends RecyclerView.Adapter<BookmarkStoreHouseAdapter.ViewHolder>{
    Context context;
    ArrayList<OtherTopicItem> otherTopicItems;


    @NonNull
    @Override
    public BookmarkStoreHouseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmark_storehouse_item_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkStoreHouseAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
        }
    }
}
