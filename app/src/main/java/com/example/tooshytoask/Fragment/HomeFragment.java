package com.example.tooshytoask.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tooshytoask.Activity.Setting.NotificationsActivity;
import com.example.tooshytoask.Activity.Setting.UpdateProfileActivity;
import com.example.tooshytoask.Adapters.CategoryAdapter;
import com.example.tooshytoask.Adapters.RecentlyBlogAdapter;
import com.example.tooshytoask.Adapters.RecommendedBlogAdapter;
import com.example.tooshytoask.Adapters.StatusAdapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.BlogItems;
import com.example.tooshytoask.Models.RecentlyBlogItems;
import com.example.tooshytoask.Models.RecommendedBlogItems;
import com.example.tooshytoask.Models.StatusItem;
import com.example.tooshytoask.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment implements View.OnClickListener{
    RecyclerView recy_recommended_blogs, recy_status, recy_recently_blogs;
    ArrayList<StatusItem>statusItems;
    ArrayList<RecommendedBlogItems>recommendedBlogItems;
    ArrayList<RecentlyBlogItems>recentlyBlogItems;
    RecommendedBlogAdapter adapter2;
    RecentlyBlogAdapter adapter3;
    StatusAdapter adapter;
    Context context;
    SPManager spManager;
    CircleImageView update_profile;
    ImageView search, notification, select_Language;

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

        statusItems.add(new StatusItem(R.drawable.status1));
        statusItems.add(new StatusItem(R.drawable.status2));
        statusItems.add(new StatusItem(R.drawable.status3));
        statusItems.add(new StatusItem(R.drawable.status3));
        statusItems.add(new StatusItem(R.drawable.status3));
        statusItems.add(new StatusItem(R.drawable.status3));
        statusItems.add(new StatusItem(R.drawable.status3));

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

        return view;
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

    }

    public void openLanguagePopup()
    {

       /* dialog1 = new Dialog(context);
        dialog1.setCancelable(true);
        dialog1.setContentView(R.layout.select_language_view);

        rel_language_en=(RelativeLayout)dialog1.findViewById(R.id.rel_language_en);
        rel_language_en.setOnClickListener(this);
        rel_language_hi=(RelativeLayout)dialog1.findViewById(R.id.rel_language_hi);
        rel_language_hi.setOnClickListener(this);
        rel_language_mr=(RelativeLayout)dialog1.findViewById(R.id.rel_language_mr);
        rel_language_mr.setOnClickListener(this);
        rel_language_gujrati=(RelativeLayout)dialog1.findViewById(R.id.rel_language_gujrati);
        rel_language_gujrati.setOnClickListener(this);
        rel_language_tamil=(RelativeLayout)dialog1.findViewById(R.id.rel_language_tamil);
        rel_language_tamil.setOnClickListener(this);

        txt_en=(TextView)dialog1.findViewById(R.id.txt_en);
        txt_hi=(TextView)dialog1.findViewById(R.id.txt_hi);
        txt_mr=(TextView)dialog1.findViewById(R.id.txt_mr);
        txt_gu=(TextView)dialog1.findViewById(R.id.txt_gu);
        txt_tamil=(TextView)dialog1.findViewById(R.id.txt_tamil);

        String selectValue=spManager.getLanguage();

        if(selectValue.equals("en"))
        {
            txt_en.setTextColor(ContextCompat.getColor(context, R.color.tsta_expert_dark));
            txt_en.setTextColor(context.getResources().getColor(R.color.tsta_expert_dark));
        }
        else if(selectValue.equals("hi"))
        {
            txt_hi.setTextColor(ContextCompat.getColor(context, R.color.tsta_expert_dark));
            txt_hi.setTextColor(context.getResources().getColor(R.color.tsta_expert_dark));
        }
        else if(selectValue.equals("mr"))
        {
            txt_mr.setTextColor(ContextCompat.getColor(context, R.color.tsta_expert_dark));
            txt_mr.setTextColor(context.getResources().getColor(R.color.tsta_expert_dark));
        }

        dialog1.show();*/
    }
}