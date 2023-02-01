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

import com.example.tooshytoask.Models.BlogItems;
import com.example.tooshytoask.Models.Blogs;
import com.example.tooshytoask.Models.StoreHouseItems;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder>{
    Context context;
    ArrayList<Blogs> Blogs;

    public BlogAdapter(Context context, ArrayList<Blogs> Blogs) {
        this.context = context;
        this.Blogs = Blogs;
    }

    @NonNull
    @Override
    public BlogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_items_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogAdapter.ViewHolder holder, int position) {
        //holder.blog_img.setImageDrawable(ContextCompat.getDrawable(context,blogs.get(position).getBlog_img()));
        //holder.save_img.setImageDrawable(ContextCompat.getDrawable(context,blogs.get(position).getSave_img()));
       // holder.blog_title.setText(blogs.get(position).getBlogContent());

    }

    @Override
    public int getItemCount() {
        return Blogs.size();
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
