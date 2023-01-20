package com.example.tooshytoask.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.Models.EventBlogItems;
import com.example.tooshytoask.Models.JustAddedBlogItems;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class JustAddedBlogAdapter extends RecyclerView.Adapter<JustAddedBlogAdapter.ViewHolder>{
    Context context;
    ArrayList<JustAddedBlogItems>justAddedBlogItems;

    public JustAddedBlogAdapter(Context context, ArrayList<JustAddedBlogItems>justAddedBlogItems) {
        this.context = context;
        this.justAddedBlogItems = justAddedBlogItems;
    }

    @NonNull
    @Override
    public JustAddedBlogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_items_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JustAddedBlogAdapter.ViewHolder holder, int position) {
        holder.blog_img.setImageDrawable(ContextCompat.getDrawable(context,justAddedBlogItems.get(position).getBlog_img()));
        holder.save_img.setImageDrawable(ContextCompat.getDrawable(context,justAddedBlogItems.get(position).getSave_img()));
        holder.blog_title.setText(justAddedBlogItems.get(position).getBlog_title());

    }

    @Override
    public int getItemCount() {
        return justAddedBlogItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView blog_img, save_img;
        TextView blog_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            blog_img = itemView.findViewById(R.id.blog_img);
            save_img = itemView.findViewById(R.id.save_img);
            blog_title = itemView.findViewById(R.id.blog_title);
        }
    }
}
