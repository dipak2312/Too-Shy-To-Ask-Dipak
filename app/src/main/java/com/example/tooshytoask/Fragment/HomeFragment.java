package com.example.tooshytoask.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tooshytoask.Activity.Landing.SignUpActivity;
import com.example.tooshytoask.Activity.Search.SearchActivity;
import com.example.tooshytoask.Activity.Setting.NotificationsActivity;
import com.example.tooshytoask.Activity.Setting.UpdateProfileActivity;
import com.example.tooshytoask.Adapters.CategoryAdapter;
import com.example.tooshytoask.Adapters.RecentlyBlogAdapter;
import com.example.tooshytoask.Adapters.RecommendedBlogAdapter;
import com.example.tooshytoask.Adapters.SliderBannerAdapter;
import com.example.tooshytoask.Adapters.StatusAdapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.BlogItems;
import com.example.tooshytoask.Models.RecentlyBlogItems;
import com.example.tooshytoask.Models.RecommendedBlogItems;
import com.example.tooshytoask.Models.SliderBannerItem;
import com.example.tooshytoask.Models.StatusItem;
import com.example.tooshytoask.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment implements View.OnClickListener{
    RecyclerView recy_recommended_blogs, recy_status, recy_recently_blogs;
    ArrayList<StatusItem>statusItems;
    ArrayList<RecommendedBlogItems>recommendedBlogItems;
    ArrayList<RecentlyBlogItems>recentlyBlogItems;
    ArrayList<SliderBannerItem> sliderBannerItems;
    RecommendedBlogAdapter adapter2;
    RecentlyBlogAdapter adapter3;
    StatusAdapter adapter;
    SliderBannerAdapter adapter4;
    Context context;
    SPManager spManager;
    CircleImageView update_profile;
    ImageView search, notification, select_Language;
    RadioButton eng_lang, hindi_lang, marathi_lang;
    Button btn_select;
    ImageView back_arrow;
    ViewPager2 viewPager2;
    Handler handler = new Handler();
    DotsIndicator mBarLayout;
    BottomSheetDialog bottomSheetDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = getActivity();
        spManager = new SPManager(context);
        recy_status = view.findViewById(R.id.recy_status);
        update_profile = view.findViewById(R.id.update_profile);
        update_profile.setOnClickListener(this);
        search = view.findViewById(R.id.search);
        search.setOnClickListener(this);
        notification = view.findViewById(R.id.notification);
        notification.setOnClickListener(this);
        select_Language = view.findViewById(R.id.select_Language);
        select_Language.setOnClickListener(this);


        statusItems = new ArrayList<>();

        statusItems.add(new StatusItem(R.drawable.status1, "Reproduction"));
        statusItems.add(new StatusItem(R.drawable.status2, "Women Safety"));
        statusItems.add(new StatusItem(R.drawable.status3, "Sex & Sex Education"));
        statusItems.add(new StatusItem(R.drawable.status3, "Mental Stress"));
        statusItems.add(new StatusItem(R.drawable.status3, "Reproduction"));
        statusItems.add(new StatusItem(R.drawable.status3, "Reproduction"));
        statusItems.add(new StatusItem(R.drawable.status3, "Sex & Sex Education"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recy_status.setLayoutManager(linearLayoutManager);

        adapter = new StatusAdapter(context ,statusItems);
        recy_status.setAdapter(adapter);

        recy_recommended_blogs = view.findViewById(R.id.recy_recommended_blogs);
        recommendedBlogItems = new ArrayList<>();

        recommendedBlogItems.add(new RecommendedBlogItems(R.drawable.blog2, R.drawable.save, "A Teenagers Guide To Building Self Confidence"));
        recommendedBlogItems.add(new RecommendedBlogItems(R.drawable.blog1, R.drawable.save, "Dominance Of Partner – Controlling"));
        recommendedBlogItems.add(new RecommendedBlogItems(R.drawable.blog3, R.drawable.save, "Emergency Contrace- ption Is it being"));
        recommendedBlogItems.add(new RecommendedBlogItems(R.drawable.blog2, R.drawable.save, "Obesity – much more than a cosmetic"));

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recy_recommended_blogs.setLayoutManager(linearLayoutManager1);

        adapter2 = new RecommendedBlogAdapter(context ,recommendedBlogItems);
        recy_recommended_blogs.setAdapter(adapter2);

        recy_recently_blogs = view.findViewById(R.id.recy_recently_blogs);
        recentlyBlogItems = new ArrayList<>();

        recentlyBlogItems.add(new RecentlyBlogItems(R.drawable.blog2, R.drawable.save, "Obesity – much more than a cosmetic"));
        recentlyBlogItems.add(new RecentlyBlogItems(R.drawable.blog1, R.drawable.save, "Dominance Of Partner – Controlling"));
        recentlyBlogItems.add(new RecentlyBlogItems(R.drawable.blog3, R.drawable.save, "Emergency Contrace- ption Is it being"));
        recentlyBlogItems.add(new RecentlyBlogItems(R.drawable.blog2, R.drawable.save, "A Teenagers Guide To Building Self Confidence"));

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recy_recently_blogs.setLayoutManager(linearLayoutManager2);

        adapter3 = new RecentlyBlogAdapter(context ,recentlyBlogItems);
        recy_recently_blogs.setAdapter(adapter3);

        //setting slider ViewPager Adapter
        viewPager2 = view.findViewById(R.id.view_pager_img);
        mBarLayout = view.findViewById(R.id.indicator_layout);
        sliderBannerItems = new ArrayList<>();
        sliderBannerItems.add(new SliderBannerItem(R.drawable.banner1));
        sliderBannerItems.add(new SliderBannerItem(R.drawable.banner2));
        adapter4 = new SliderBannerAdapter(sliderBannerItems, viewPager2);
        viewPager2.setAdapter(adapter4);
        viewPager2.setClipChildren(false);
        viewPager2.setClipToPadding(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(8));
        //mBarLayout.setViewPager2(viewPager2);
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 0 - Math.abs(position);
//                page.setScaleY(0.90f + r* 0.10f);//this will reduce next position height during transformation
                page.setScaleY(1f);   // this will set both height same during transformation
                page.setScaleX(0.90f + 0.10f);// it is 1.00f value is default

            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (getitem(0) < sliderBannerItems.size()) {

                   // mBarLayout.setViewPager2(viewPager2);

                }
                handler.removeCallbacks(sliderRunnable);
                handler.postDelayed(sliderRunnable, 5000);
            }
        });

        return view;
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    private int getitem(int i) {

        return viewPager2.getCurrentItem() + i;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == update_profile.getId()) {
            Intent intent = new Intent(context, UpdateProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else if (id == notification.getId()) {
            Intent intent = new Intent(context, NotificationsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else if(id==select_Language.getId()) {

            openLanguagePopup();
        }
        else if(id==search.getId()) {
            Intent intent = new Intent(context, SearchActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
        else if(id==eng_lang.getId())
        {
            setLocale("en");
            btn_select.setText(R.string.select);
            eng_lang.setBackgroundResource(R.drawable.language_background_active);
            hindi_lang.setBackgroundResource(R.drawable.language_background);
            marathi_lang.setBackgroundResource(R.drawable.language_background);
        }
        else if(id==hindi_lang.getId())
        {
            setLocale("hi");
            btn_select.setText(R.string.चुनें);
            hindi_lang.setBackgroundResource(R.drawable.language_background_active);
            eng_lang.setBackgroundResource(R.drawable.language_background);
            marathi_lang.setBackgroundResource(R.drawable.language_background);
        }
        else if(id==marathi_lang.getId())
        {
            setLocale("mr");
            btn_select.setText(R.string.निवडा);
            marathi_lang.setBackgroundResource(R.drawable.language_background_active);
            eng_lang.setBackgroundResource(R.drawable.language_background);
            hindi_lang.setBackgroundResource(R.drawable.language_background);
        }
        else if(id==btn_select.getId())
        {
            bottomSheetDialog.dismiss();
            refreshFragment();
        }
        else if(id==back_arrow.getId())
        {
            bottomSheetDialog.dismiss();
        }

    }

    public void openLanguagePopup()
    {

        bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(R.layout.select_language_view);
        bottomSheetDialog.setCancelable(false);

        eng_lang=bottomSheetDialog.findViewById(R.id.eng_lang);
        eng_lang.setOnClickListener(this);
        hindi_lang=bottomSheetDialog.findViewById(R.id.hindi_lang);
        hindi_lang.setOnClickListener(this);
        marathi_lang=bottomSheetDialog.findViewById(R.id.marathi_lang);
        marathi_lang.setOnClickListener(this);
        back_arrow = bottomSheetDialog.findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(this);
        btn_select = bottomSheetDialog.findViewById(R.id.btn_select);
        btn_select.setOnClickListener(this);


        String selectValue=spManager.getLanguage();

        if(selectValue.equals("en"))
        {

            eng_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
            eng_lang.setTextColor(context.getResources().getColor(R.color.black));
        }
        else if(selectValue.equals("hi"))
        {

            hindi_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
            hindi_lang.setTextColor(context.getResources().getColor(R.color.black));

        }
        else if(selectValue.equals("mr"))
        {

            marathi_lang.setTextColor(ContextCompat.getColor(context, R.color.black));
            marathi_lang.setTextColor(context.getResources().getColor(R.color.black));

        }

        bottomSheetDialog.show();
    }

    public void refreshFragment()
    {
        HomeFragment fragment1=new HomeFragment();
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.rootLayout,fragment1);
        ft.commit();
    }

    private void setLocale(String lang) {

        Locale locale=new Locale(lang);
        Locale.setDefault(locale);

        Configuration config=new Configuration();
        config.locale=locale;
        getActivity().getResources().updateConfiguration(config,getActivity().getResources().getDisplayMetrics());
        spManager.setLanguage(lang);


    }
}