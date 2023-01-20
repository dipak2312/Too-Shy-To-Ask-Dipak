package com.example.tooshytoask.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tooshytoask.Activity.Blogs.AllBlogsActivity;
import com.example.tooshytoask.Adapters.BlogAdapter;
import com.example.tooshytoask.Adapters.CoursesAdapter;
import com.example.tooshytoask.Adapters.EventBlogAdapter;
import com.example.tooshytoask.Adapters.HighlightBlogAdapter;
import com.example.tooshytoask.Adapters.JustAddedBlogAdapter;
import com.example.tooshytoask.Adapters.StatusAdapter;
import com.example.tooshytoask.Adapters.StoreHouseAdapter;
import com.example.tooshytoask.Adapters.VideoGalleryAdapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.BlogItems;
import com.example.tooshytoask.Models.CoursesItems;
import com.example.tooshytoask.Models.EventBlogItems;
import com.example.tooshytoask.Models.HighlightBlogItems;
import com.example.tooshytoask.Models.JustAddedBlogItems;
import com.example.tooshytoask.Models.StoreHouseItems;
import com.example.tooshytoask.Models.VideoGalleryItems;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class InsightsFragment extends Fragment implements View.OnClickListener{
    Context context;
    SPManager spManager;
    RecyclerView recy_storehouse, recy_blogs, recy_highlight_blogs, recy_event_blogs,
                 recy_just_added_blogs, recy_courses, recy_video_gallery;
    ArrayList<BlogItems>blogItems;
    ArrayList<StoreHouseItems>storeHouseItems;
    ArrayList<HighlightBlogItems>highlightBlogItem;
    ArrayList<EventBlogItems>eventBlogItems;
    ArrayList<JustAddedBlogItems>justAddedBlogItems;
    ArrayList<CoursesItems>coursesItems;
    ArrayList<VideoGalleryItems>videoGalleryItems;
    VideoGalleryAdapter videoGalleryAdapter;
    CoursesAdapter coursesAdapter;
    EventBlogAdapter eventBlogAdapter;
    JustAddedBlogAdapter justAddedBlogAdapter;
    StoreHouseAdapter adapter;
    HighlightBlogAdapter highlightBlogAdapter;
    BlogAdapter blogAdapter;
    TextView see_all;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insights, container, false);
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = getActivity();
        spManager = new SPManager(context);

        recy_storehouse = view.findViewById(R.id.recy_storehouse);
        see_all = view.findViewById(R.id.see_all);
        see_all.setOnClickListener(this);

        storeHouseItems = new ArrayList<>();

        storeHouseItems.add(new StoreHouseItems(R.drawable.relationships, "Relationships"));
        storeHouseItems.add(new StoreHouseItems(R.drawable.sexuality, "Sex & Sexuality"));
        storeHouseItems.add(new StoreHouseItems(R.drawable.reproduction, "Reproduction"));
        storeHouseItems.add(new StoreHouseItems(R.drawable.reproduction, "Mental Health"));
        storeHouseItems.add(new StoreHouseItems(R.drawable.education, "Education"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recy_storehouse.setLayoutManager(linearLayoutManager);

        adapter = new StoreHouseAdapter(context ,storeHouseItems);
        recy_storehouse.setAdapter(adapter);

        recy_blogs = view.findViewById(R.id.recy_blogs);
        blogItems = new ArrayList<>();

        blogItems.add(new BlogItems(R.drawable.blog2, R.drawable.save, "Obesity – much more than a cosmetic"));
        blogItems.add(new BlogItems(R.drawable.blog1, R.drawable.save, "Dominance Of Partner – Controlling"));
        blogItems.add(new BlogItems(R.drawable.blog3, R.drawable.save, "Obesity – much more than a cosmetic"));
        blogItems.add(new BlogItems(R.drawable.blog2, R.drawable.save, "Dominance Of Partner – Controlling"));

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recy_blogs.setLayoutManager(linearLayoutManager1);

        blogAdapter = new BlogAdapter(context ,blogItems);
        recy_blogs.setAdapter(blogAdapter);

        recy_highlight_blogs = view.findViewById(R.id.recy_highlight_blogs);
        highlightBlogItem = new ArrayList<>();

        highlightBlogItem.add(new HighlightBlogItems(R.drawable.blog2, R.drawable.save, "Obesity – much more than a cosmetic"));
        highlightBlogItem.add(new HighlightBlogItems(R.drawable.blog1, R.drawable.save, "Dominance Of Partner – Controlling"));
        highlightBlogItem.add(new HighlightBlogItems(R.drawable.blog3, R.drawable.save, "Obesity – much more than a cosmetic"));
        highlightBlogItem.add(new HighlightBlogItems(R.drawable.blog2, R.drawable.save, "Dominance Of Partner – Controlling"));

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recy_highlight_blogs.setLayoutManager(linearLayoutManager2);

        highlightBlogAdapter = new HighlightBlogAdapter(context ,highlightBlogItem);
        recy_highlight_blogs.setAdapter(highlightBlogAdapter);

        recy_event_blogs = view.findViewById(R.id.recy_event_blogs);
        eventBlogItems = new ArrayList<>();

        eventBlogItems.add(new EventBlogItems(R.drawable.blog2, R.drawable.save, "Obesity – much more than a cosmetic"));
        eventBlogItems.add(new EventBlogItems(R.drawable.blog1, R.drawable.save, "Dominance Of Partner – Controlling"));
        eventBlogItems.add(new EventBlogItems(R.drawable.blog3, R.drawable.save, "Obesity – much more than a cosmetic"));
        eventBlogItems.add(new EventBlogItems(R.drawable.blog2, R.drawable.save, "Dominance Of Partner – Controlling"));

        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recy_event_blogs.setLayoutManager(linearLayoutManager3);

        eventBlogAdapter = new EventBlogAdapter(context ,eventBlogItems);
        recy_event_blogs.setAdapter(eventBlogAdapter);

        recy_just_added_blogs = view.findViewById(R.id.recy_just_added_blogs);
        justAddedBlogItems = new ArrayList<>();

        justAddedBlogItems.add(new JustAddedBlogItems(R.drawable.blog2, R.drawable.save, "Obesity – much more than a cosmetic"));
        justAddedBlogItems.add(new JustAddedBlogItems(R.drawable.blog1, R.drawable.save, "Dominance Of Partner – Controlling"));
        justAddedBlogItems.add(new JustAddedBlogItems(R.drawable.blog3, R.drawable.save, "Obesity – much more than a cosmetic"));
        justAddedBlogItems.add(new JustAddedBlogItems(R.drawable.blog2, R.drawable.save, "Dominance Of Partner – Controlling"));

        LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recy_just_added_blogs.setLayoutManager(linearLayoutManager4);

        justAddedBlogAdapter = new JustAddedBlogAdapter(context ,justAddedBlogItems);
        recy_just_added_blogs.setAdapter(justAddedBlogAdapter);

        recy_courses = view.findViewById(R.id.recy_courses);
        coursesItems = new ArrayList<>();

        coursesItems.add(new CoursesItems(R.drawable.courses1, R.drawable.save, "Obesity – much more than a cosmetic", "1h 15m","7 Lessons"));
        coursesItems.add(new CoursesItems(R.drawable.courses2, R.drawable.save, "Dominance Of Partner – Controlling", "2h 05m","12 Lessons"));
        coursesItems.add(new CoursesItems(R.drawable.courses3, R.drawable.save, "Obesity – much more than a cosmetic", "1h 00m","5 Lessons"));
        coursesItems.add(new CoursesItems(R.drawable.courses1, R.drawable.save, "Dominance Of Partner – Controlling", "1h 40m","9 Lessons"));

        LinearLayoutManager linearLayoutManager5 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recy_courses.setLayoutManager(linearLayoutManager5);

        coursesAdapter = new CoursesAdapter(context ,coursesItems);
        recy_courses.setAdapter(coursesAdapter);

        recy_video_gallery = view.findViewById(R.id.recy_video_gallery);
        videoGalleryItems = new ArrayList<>();

        videoGalleryItems.add(new VideoGalleryItems(R.drawable.blog2, R.drawable.save, R.drawable.video_img,"Obesity – much more than a cosmetic"));
        videoGalleryItems.add(new VideoGalleryItems(R.drawable.blog1, R.drawable.save, R.drawable.video_img,"Dominance Of Partner – Controlling"));
        videoGalleryItems.add(new VideoGalleryItems(R.drawable.blog3, R.drawable.save, R.drawable.video_img,"Obesity – much more than a cosmetic"));
        videoGalleryItems.add(new VideoGalleryItems(R.drawable.blog2, R.drawable.save, R.drawable.video_img,"Dominance Of Partner – Controlling"));

        LinearLayoutManager linearLayoutManager6 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recy_video_gallery.setLayoutManager(linearLayoutManager6);

        videoGalleryAdapter = new VideoGalleryAdapter(context ,videoGalleryItems);
        recy_video_gallery.setAdapter(videoGalleryAdapter);

        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == see_all.getId()) {
            Intent intent = new Intent(context, AllBlogsActivity.class);
            startActivity(intent);
        }

    }
}