package com.example.tooshytoask.Activity.Bookmark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.tooshytoask.Adapters.BookmarkBlogAdapter;
import com.example.tooshytoask.Adapters.BookmarkCoursesAdapter;
import com.example.tooshytoask.Adapters.BookmarkEventAdapter;
import com.example.tooshytoask.Adapters.BookmarkStoreHouseAdapter;
import com.example.tooshytoask.Adapters.BookmarkVideoGalleryAdapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.R;

public class BookmarkActivity extends AppCompatActivity implements View.OnClickListener {
    SPManager spManager;
    Context context;
    RecyclerView storehouse_saved_recy, blog_saved_recy, event_saved_recy, courses_saved_recy,videos_saved_recy;
    RelativeLayout rel_back,rel_storehouse_saved_items,rel_blog_saved_items, rel_event_saved_items,
            rel_courses_saved_items, rel_videos_saved_items;
    BookmarkCoursesAdapter bookmarkCoursesAdapter;
    BookmarkBlogAdapter bookmarkBlogAdapter;
    BookmarkStoreHouseAdapter bookmarkStoreHouseAdapter;
    BookmarkEventAdapter bookmarkEventAdapter;
    BookmarkVideoGalleryAdapter bookmarkVideoGalleryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = BookmarkActivity.this;
        spManager = new SPManager(context);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        rel_storehouse_saved_items = findViewById(R.id.rel_storehouse_saved_items);
        rel_blog_saved_items = findViewById(R.id.rel_blog_saved_items);
        rel_event_saved_items = findViewById(R.id.rel_event_saved_items);
        rel_courses_saved_items = findViewById(R.id.rel_courses_saved_items);
        rel_videos_saved_items = findViewById(R.id.rel_videos_saved_items);

        storehouse_saved_recy = findViewById(R.id.storehouse_saved_recy);
        storehouse_saved_recy.setLayoutManager(new GridLayoutManager(context,2, GridLayoutManager.VERTICAL, false));

        blog_saved_recy = findViewById(R.id.blog_saved_recy);
        blog_saved_recy.setLayoutManager(new GridLayoutManager(context,2, GridLayoutManager.VERTICAL, false));

        event_saved_recy = findViewById(R.id.event_saved_recy);
        event_saved_recy.setLayoutManager(new GridLayoutManager(context,2, GridLayoutManager.VERTICAL, false));

        courses_saved_recy = findViewById(R.id.courses_saved_recy);
        courses_saved_recy.setLayoutManager(new GridLayoutManager(context,2, GridLayoutManager.VERTICAL, false));

        videos_saved_recy = findViewById(R.id.videos_saved_recy);
        videos_saved_recy.setLayoutManager(new GridLayoutManager(context,2, GridLayoutManager.VERTICAL, false));
    }

    @Override
    public void onClick(View view) {

    }
}