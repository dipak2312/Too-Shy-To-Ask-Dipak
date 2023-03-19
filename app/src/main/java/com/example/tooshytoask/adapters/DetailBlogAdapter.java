package com.example.tooshytoask.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.R;

public class DetailBlogAdapter extends RecyclerView.Adapter<DetailBlogAdapter.ViewHolder> {
    Context context;

    @NonNull
    @Override
    public DetailBlogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_title_item_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailBlogAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView blog_title;
        RecyclerView recy_blog_desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recy_blog_desc = itemView.findViewById(R.id.recy_blog_desc);
            blog_title = itemView.findViewById(R.id.blog_title);

            if(recy_blog_desc !=null) {
                LinearLayoutManager lm = new LinearLayoutManager(context);
                lm.setOrientation(RecyclerView.VERTICAL);
                recy_blog_desc.setLayoutManager(lm);
            }
        }
    }
}
