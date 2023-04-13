package com.neuronimbus.metropolis.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.neuronimbus.metropolis.activity.InformationStoreHouse.InformationStorehouseActivity;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.InsightScreen.storeHouse;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.GuestLoginPopup;

import java.util.ArrayList;

public class StoreHouseAdapter extends RecyclerView.Adapter<StoreHouseAdapter.ViewHolder> {
    Context context;
    ArrayList<storeHouse> storeHouse;
    SPManager spManager;

    public StoreHouseAdapter(Context context, ArrayList<storeHouse> storeHouse, SPManager spManager) {
        this.context = context;
        this.storeHouse = storeHouse;
        this.spManager = spManager;
    }

    @NonNull
    @Override
    public StoreHouseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.storehouse_item_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreHouseAdapter.ViewHolder holder, int position) {

        if (spManager.getTstaguestLoginStatus().equals("false")) {
            Glide.with(context).load(storeHouse.get(position).getTitle_image()).into(holder.storehouse_items_img);
            holder.storehouse_item_txt.setText(storeHouse.get(position).getTitle());
        }
        else {
            Glide.with(context).load(storeHouse.get(position).getTitle_image()).into(holder.storehouse_items_img);
            holder.storehouse_items_img.setRenderEffect(RenderEffect.createBlurEffect(20,20, Shader.TileMode.MIRROR));
            holder.lock_fent_img.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return storeHouse.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView storehouse_items_img, lock_fent_img;
        TextView storehouse_item_txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            storehouse_items_img = itemView.findViewById(R.id.storehouse_items_img);
            storehouse_item_txt = itemView.findViewById(R.id.storehouse_item_txt);
            lock_fent_img = itemView.findViewById(R.id.lock_fent_img);

            storehouse_items_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (spManager.getTstaguestLoginStatus().equals("false")) {
                    Bundle bundle = new Bundle();

                    bundle.putString("title_id", storeHouse.get(getAdapterPosition()).getTitle_id());
                    Intent intent = new Intent(context, InformationStorehouseActivity.class);
                    intent.putExtras(bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    }
                    else {
                        GuestLoginPopup.LogOut(context, spManager);
                    }
                }
            });
        }
    }
}
