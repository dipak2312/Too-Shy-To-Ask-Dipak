package com.example.tooshytoask.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.tooshytoask.Fragment.SplashScreenFragment.FirstSplashFragment;
import com.example.tooshytoask.Fragment.SplashScreenFragment.SecondSplashFragment;
import com.example.tooshytoask.Fragment.SplashScreenFragment.ThirdSplashFragment;
import com.example.tooshytoask.Models.OnboardingList;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    Context context;
   ArrayList<OnboardingList> onboardingLists;

    public ViewPagerAdapter(@NonNull FragmentManager fm, ArrayList<OnboardingList> onboardingLists) {

        super(fm);
        this.onboardingLists=onboardingLists;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            FirstSplashFragment first = new FirstSplashFragment(onboardingLists);
            return first;

        } else if (position == 1) {

            SecondSplashFragment second = new SecondSplashFragment(onboardingLists);
            return second;

        } else if (position == 2) {

            ThirdSplashFragment third = new ThirdSplashFragment(onboardingLists);
            return third;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}



/*public class OnBoradingAdapter extends RecyclerView.Adapter<OnBoradingAdapter.ViewHolder>{
    Context context;
    ArrayList<OnboardingList> onboardingLists;

    public OnBoradingAdapter(Context context, ArrayList<OnboardingList> onboardingLists) {
        this.context = context;
        this.onboardingLists = onboardingLists;
    }

    @NonNull
    @Override
    public OnBoradingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnBoradingAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_item_img,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OnBoradingAdapter.ViewHolder holder, int position) {
        holder.title.setText(onboardingLists.get(position).getTitle());
        holder.txt_desc.setText(onboardingLists.get(position).getDesc());
        Glide.with(context).load(onboardingLists.get(position).getImg()).placeholder(R.drawable.welcome).into(holder.img_slide);
    }


    @Override
    public int getItemCount() {
        return onboardingLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,txt_desc;
        ImageView img_slide;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title=(TextView)itemView.findViewById(R.id.title);
            txt_desc=(TextView)itemView.findViewById(R.id.txt_desc);
            img_slide=(ImageView)itemView.findViewById(R.id.img_slide);
        }
    }
}*/


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

