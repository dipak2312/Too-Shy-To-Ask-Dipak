package com.example.tooshytoask.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.tooshytoask.Models.SliderItem;
import com.example.tooshytoask.Models.SliderText;
import com.example.tooshytoask.R;
import com.example.tooshytoask.databinding.SlideItemImgBinding;

import java.util.List;

public class SliderImageAdapter extends RecyclerView.Adapter<SliderImageAdapter.ViewHolder>{
    List<SliderItem> sliderItems;
    ViewPager2 viewPager2;


    public SliderImageAdapter(List<SliderItem> sliderItems, ViewPager2 viewPager2) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //SlideItemImgBinding binding = SlideItemImgBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_item_img,parent,false));
        //return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderImageAdapter.ViewHolder holder, int position) {
        holder.setImage(sliderItems.get(position));
        /*if (position == sliderItems.size() - 2){
            viewPager2.post(sliderRunnable);
        }*/

    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_slide;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_slide = itemView.findViewById(R.id.img_slide);

        }

        void setImage(SliderItem sliderItem){

            //if we want to use Glide and set image from API we can set here
            img_slide.setImageResource(sliderItem.getImage());
        }
    }

    /*private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            sliderItems.addAll(sliderItems);
            notifyDataSetChanged();
        }
    };*/
}
