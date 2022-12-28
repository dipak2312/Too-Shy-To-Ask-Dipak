package com.example.tooshytoask.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.tooshytoask.Fragment.SplashScreenFragment.FirstSplashFragment;
import com.example.tooshytoask.Fragment.SplashScreenFragment.SecondSplashFragment;
import com.example.tooshytoask.Fragment.SplashScreenFragment.ThirdSplashFragment;
import com.example.tooshytoask.Models.SliderItem;
import com.example.tooshytoask.R;

import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0){

            FirstSplashFragment first=new FirstSplashFragment();
            return first;

        }
        else if(position==1){

            SecondSplashFragment second=new SecondSplashFragment();
            return second;

        }
        else if(position==2){

            ThirdSplashFragment third=new ThirdSplashFragment();
            return third;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    /*List<SliderItem> sliderItems;
    ViewPager2 viewPager2;


    public ViewPagerAdapter(List<SliderItem> sliderItems, ViewPager2 viewPager2) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public ViewPagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //SlideItemImgBinding binding = SlideItemImgBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_item_img,parent,false));
        //return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerAdapter.ViewHolder holder, int position) {
        holder.setImage(sliderItems.get(position));
        if (position == sliderItems.size() - 2){
            viewPager2.post(sliderRunnable);
        }

    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }*/



   /* public class ViewHolder extends RecyclerView.ViewHolder {
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

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            sliderItems.addAll(sliderItems);
            notifyDataSetChanged();
        }
    };*/
}
