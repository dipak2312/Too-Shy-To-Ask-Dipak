package com.example.tooshytoask.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.Models.Bannerist;
import com.example.tooshytoask.R;
import com.example.tooshytoask.activity.Blogs.AllBlogsActivity;
import com.example.tooshytoask.activity.Blogs.DetailBlogActivity;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class SliderBannerAdapter extends RecyclerView.Adapter<SliderBannerAdapter.SliderViewHolder> {
    ArrayList<Bannerist> Bannerist;
    Context context;
    private ViewPager2 viewPager2;

    public SliderBannerAdapter(ArrayList<Bannerist> Bannerist, ViewPager2 viewPager2, Context context) {
        this.Bannerist = Bannerist;
        this.viewPager2 = viewPager2;
        this.context = context;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_banner,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setImage(Bannerist.get(position));
        if (position == Bannerist.size()){
            viewPager2.post(sliderRunnable);
        }

        holder.imageSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Bannerist.get(position).getBanner_screen().equals("category")){
                    Bundle bundle = new Bundle();
                    bundle.putString("blog_id",Bannerist.get(position).getBanner_blogid());
                    Intent intent = new Intent(context, AllBlogsActivity.class);
                    intent.putExtras(bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
                else if (Bannerist.get(position).getBanner_screen().equals("blog")){

                    Bundle bundle = new Bundle();
                    bundle.putString("blog_id",Bannerist.get(position).getBanner_blogid());
                    Intent intent = new Intent(context, DetailBlogActivity.class);
                    intent.putExtras(bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return Bannerist.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder{
        ShapeableImageView imageSlide;
        SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageSlide = itemView.findViewById(R.id.imageSlide);


        }
        void setImage(Bannerist Bannerist){

            //if we want to use Glide and set image from API we can set here
            Glide.with(context).load(Bannerist.getBanner_img()).into(imageSlide);
        }
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            Bannerist.addAll(Bannerist);
            notifyDataSetChanged();
        }
    };
}