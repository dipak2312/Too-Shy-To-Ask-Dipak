package com.neuronimbus.metropolis.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.neuronimbus.metropolis.activity.Blogs.DetailEventActivity;
import com.neuronimbus.metropolis.activity.InformationStoreHouse.InformationStoreHouseDetailActivity;
import com.neuronimbus.metropolis.Models.event_search;
import com.neuronimbus.metropolis.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class EventSearchAdapter extends RecyclerView.Adapter<EventSearchAdapter.ViewHolder> {
    Context context;
    ArrayList<event_search>Allevent_search;

    public EventSearchAdapter(Context context, ArrayList<event_search> allevent_search) {
        this.context = context;
        Allevent_search = allevent_search;
    }


    @NonNull
    @Override
    public EventSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_storehouse_search_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventSearchAdapter.ViewHolder holder, int position) {
        holder.blog_title.setText(Html.fromHtml(Allevent_search.get(position).getBlog_title()));
        Glide.with(context).load(Allevent_search.get(position).getBlog_img()).into(holder.blog_img);
    }

    @Override
    public int getItemCount() {
        return Allevent_search.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView blog_img;
        TextView blog_title;
        LinearLayout info_storHouse_lay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            blog_title = itemView.findViewById(R.id.blog_title);
            blog_img = itemView.findViewById(R.id.blog_img);
            info_storHouse_lay = itemView.findViewById(R.id.info_storHouse_lay);

            info_storHouse_lay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();

                    bundle.putString("blog_id",Allevent_search.get(getAdapterPosition()).getBlog_id());
                    Intent intent = new Intent(context, DetailEventActivity.class);
                    intent.putExtras(bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
