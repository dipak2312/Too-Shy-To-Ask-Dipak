package com.example.tooshytoask.Adapters;

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
import com.example.tooshytoask.Activity.InformationStoreHouse.InformationStoreHouseDetailActivity;
import com.example.tooshytoask.Models.blog_search;
import com.example.tooshytoask.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class BlogSearchAdapter extends RecyclerView.Adapter<BlogSearchAdapter.ViewHolder> {
    Context context;
    ArrayList<blog_search> Allblog_search;

    public BlogSearchAdapter(Context context, ArrayList<blog_search> allblog_search) {
        this.context = context;
        Allblog_search = allblog_search;
    }

    @NonNull
    @Override
    public BlogSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_storehouse_search_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogSearchAdapter.ViewHolder holder, int position) {
        holder.blog_title.setText(Html.fromHtml(Allblog_search.get(position).getBlog_title()));
        Glide.with(context).load(Allblog_search.get(position).getBlog_img()).into(holder.blog_img);
    }

    @Override
    public int getItemCount() {
        return Allblog_search.size();
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

                    bundle.putString("blog_id",Allblog_search.get(getAdapterPosition()).getBlog_id());
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
