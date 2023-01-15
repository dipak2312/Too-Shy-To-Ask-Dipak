package com.example.tooshytoask.Adapters;

import android.content.Context;
import android.provider.Contacts;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.Models.StoreHouseItems;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class StoreHouseAdapter extends RecyclerView.Adapter<StoreHouseAdapter.ViewHolder> {
    Context context;
    ArrayList<StoreHouseItems> storeHouseItems;

    public StoreHouseAdapter(Context context, ArrayList<StoreHouseItems> storeHouseItems) {
        this.context = context;
        this.storeHouseItems = storeHouseItems;
    }

    @NonNull
    @Override
    public StoreHouseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.storehouse_item_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreHouseAdapter.ViewHolder holder, int position) {
        holder.storehouse_items_img.setImageDrawable(ContextCompat.getDrawable(context,storeHouseItems.get(position).getStorehouse_items_img()));
        holder.storehouse_item_txt.setText(storeHouseItems.get(position).getStorehouse_item_txt());

    }

    @Override
    public int getItemCount() {
        return storeHouseItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView storehouse_items_img;
        TextView storehouse_item_txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            storehouse_items_img = itemView.findViewById(R.id.storehouse_items_img);
            storehouse_item_txt = itemView.findViewById(R.id.storehouse_item_txt);
        }
    }
}
