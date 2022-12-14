package com.example.tooshytoask.Activity.Landing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.tooshytoask.Adapters.SliderImageAdapter;
import com.example.tooshytoask.Models.SliderItem;
import com.example.tooshytoask.R;
import com.example.tooshytoask.databinding.ActivitySliderImagesBinding;

import java.util.ArrayList;
import java.util.List;

public class SliderImages extends AppCompatActivity {
    ActivitySliderImagesBinding binding;
    SliderImageAdapter adapter;
    List<SliderItem> sliderItems;
    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySliderImagesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //adapter = new SliderImageAdapter();



        //setting slider ViewPager Adapter
        sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.img1));
        sliderItems.add(new SliderItem(R.drawable.img2));
        sliderItems.add(new SliderItem(R.drawable.img3));
        sliderItems.add(new SliderItem(R.drawable.img4));
        adapter = new SliderImageAdapter(sliderItems, viewPager2);
        viewPager2.setAdapter(adapter);
        viewPager2.setClipChildren(false);
        viewPager2.setClipToPadding(false);
        viewPager2.setOffscreenPageLimit(4);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
    }
}