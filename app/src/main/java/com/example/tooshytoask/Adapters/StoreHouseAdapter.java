package com.example.tooshytoask.Adapters;

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
import com.example.tooshytoask.Activity.InformationStoreHouse.InformationStoreHouseDetailActivity;
import com.example.tooshytoask.Activity.InformationStoreHouse.InformationStorehouseActivity;
import com.example.tooshytoask.Models.InsightScreen.storeHouse;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class StoreHouseAdapter extends RecyclerView.Adapter<StoreHouseAdapter.ViewHolder> {
    Context context;
    ArrayList<storeHouse> storeHouse;

    public StoreHouseAdapter(Context context, ArrayList<storeHouse> storeHouse) {
        this.context = context;
        this.storeHouse = storeHouse;
    }

    @NonNull
    @Override
    public StoreHouseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.storehouse_item_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreHouseAdapter.ViewHolder holder, int position) {
        //holder.storehouse_items_img.setImageDrawable(ContextCompat.getDrawable(context,BlogCategory.get(position).getCategoryImg()));
        Glide.with(context).load(storeHouse.get(position).getTitle_image()).into(holder.storehouse_items_img);
        holder.storehouse_item_txt.setText(storeHouse.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return storeHouse.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView storehouse_items_img;
        TextView storehouse_item_txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            storehouse_items_img = itemView.findViewById(R.id.storehouse_items_img);
            storehouse_item_txt = itemView.findViewById(R.id.storehouse_item_txt);

            storehouse_items_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();

                    bundle.putString("title_id", storeHouse.get(getAdapterPosition()).getTitle_id());
                    Intent intent = new Intent(context, InformationStorehouseActivity.class);
                    intent.putExtras(bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
