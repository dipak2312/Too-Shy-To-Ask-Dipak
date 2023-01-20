package com.example.tooshytoask.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.tooshytoask.Models.SliderBannerItem;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.OnClickListner;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class SliderBannerAdapter extends RecyclerView.Adapter<SliderBannerAdapter.SliderViewHolder> {
   private List<SliderBannerItem> sliderBannerItems;
   private ViewPager2 viewPager2;

   public SliderBannerAdapter(List<SliderBannerItem> sliderBannerItems, ViewPager2 viewPager2) {
      this.sliderBannerItems = sliderBannerItems;
      this.viewPager2 = viewPager2;
   }

   @NonNull
   @Override
   public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_banner,parent,false));
   }

   @Override
   public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
   holder.setImage(sliderBannerItems.get(position));
   if (position == sliderBannerItems.size()){
      viewPager2.post(sliderRunnable);
   }
   }

   @Override
   public int getItemCount() {
      return sliderBannerItems.size();
   }

   class SliderViewHolder extends RecyclerView.ViewHolder{
      ShapeableImageView imageSlide;
       SliderViewHolder(@NonNull View itemView) {
         super(itemView);
          imageSlide = itemView.findViewById(R.id.imageSlide);

      }
      void setImage(SliderBannerItem sliderBannerItems){

         //if we want to use Glide and set image from API we can set here
         imageSlide.setImageResource(sliderBannerItems.getImageSlide());
      }
   }

   public Runnable sliderRunnable = new Runnable() {
      @Override
      public void run() {
         sliderBannerItems.addAll(sliderBannerItems);
         notifyDataSetChanged();

      }
   };
}
