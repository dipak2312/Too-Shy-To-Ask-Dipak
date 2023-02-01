package com.example.tooshytoask.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.Models.Bannerist;
import com.example.tooshytoask.Models.SliderBannerItem;
import com.example.tooshytoask.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

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
        //holder.setImage(Bannerist.get(position));
        Glide.with(context).load(Bannerist.get(position).getBannerImg()).into(holder.imageSlide);
        if (position == Bannerist.size()){
            viewPager2.post(sliderRunnable);
        }
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
            //imageSlide.setImageResource(Bannerist.getBannerImg());
            Glide.with(context).load(Bannerist.getBannerImg()).into(imageSlide);
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