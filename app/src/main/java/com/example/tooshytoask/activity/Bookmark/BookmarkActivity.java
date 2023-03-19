package com.example.tooshytoask.activity.Bookmark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.adapters.BookmarkBlogAdapter;
import com.example.tooshytoask.adapters.BookmarkCoursesAdapter;
import com.example.tooshytoask.adapters.BookmarkEventAdapter;
import com.example.tooshytoask.adapters.BookmarkStoreHouseAdapter;
import com.example.tooshytoask.adapters.BookmarkVideoGalleryAdapter;
import com.example.tooshytoask.AuthModels.BookmarkAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.BookmarkResponse;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class BookmarkActivity extends AppCompatActivity implements View.OnClickListener {
    SPManager spManager;
    Context context;
    CustomProgressDialog dialog;
    RecyclerView storehouse_saved_recy, blog_saved_recy, event_saved_recy, courses_saved_recy,videos_saved_recy;
    RelativeLayout rel_back,rel_storehouse_saved_items,rel_blog_saved_items, rel_event_saved_items,
            rel_courses_saved_items, rel_videos_saved_items;
    NestedScrollView bookmark_scroll_view;
    BookmarkCoursesAdapter bookmarkCoursesAdapter;
    BookmarkBlogAdapter bookmarkBlogAdapter;
    BookmarkStoreHouseAdapter bookmarkStoreHouseAdapter;
    BookmarkEventAdapter bookmarkEventAdapter;
    BookmarkVideoGalleryAdapter bookmarkVideoGalleryAdapter;
    ArrayList<com.example.tooshytoask.Models.blog_bookmark> blog_bookmark;
    ArrayList<com.example.tooshytoask.Models.infostorehousebookmark>infostorehousebookmark;
    ArrayList<com.example.tooshytoask.Models.videobookmarks>videobookmarks;
    ArrayList<com.example.tooshytoask.Models.events>events;
    ArrayList<com.example.tooshytoask.Models.courses>courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = BookmarkActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        bookmark_scroll_view = findViewById(R.id.bookmark_scroll_view);
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

        getBookmark();
    }

    public void getBookmark(){
        dialog.show("");
        bookmark_scroll_view.setVisibility(View.GONE);

        BookmarkAuthModel model = new BookmarkAuthModel();
        model.setUser_id(spManager.getUserId());

        WebServiceModel.getRestApi().getBookmark(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<BookmarkResponse>() {
                    @Override
                    public void onNext(BookmarkResponse bookmarkResponse) {
                        String msg = bookmarkResponse.getMsg();
                        dialog.dismiss("");
                        bookmark_scroll_view.setVisibility(View.VISIBLE);
                        if (msg.equals("Success")){
                            blog_bookmark = bookmarkResponse.getBlog_bookmark();
                            infostorehousebookmark = bookmarkResponse.getInfostorehousebookmark();
                            videobookmarks = bookmarkResponse.getVideobookmarks();
                            events = bookmarkResponse.getEvents();
                            courses = bookmarkResponse.getCourses();

                            if (blog_bookmark.size() != 0){
                                rel_blog_saved_items.setVisibility(View.VISIBLE);
                                bookmarkBlogAdapter = new BookmarkBlogAdapter(context, blog_bookmark);
                                blog_saved_recy.setAdapter(bookmarkBlogAdapter);
                            }
                            if (blog_bookmark.size() == 0){
                                rel_blog_saved_items.setVisibility(View.GONE);
                            }
                            if (infostorehousebookmark.size() != 0){
                                rel_storehouse_saved_items.setVisibility(View.VISIBLE);
                                bookmarkStoreHouseAdapter = new BookmarkStoreHouseAdapter(context, infostorehousebookmark);
                                storehouse_saved_recy.setAdapter(bookmarkStoreHouseAdapter);
                            }
                            if (infostorehousebookmark.size() == 0){
                                rel_storehouse_saved_items.setVisibility(View.GONE);
                            }

                            if (videobookmarks.size() != 0){
                                rel_videos_saved_items.setVisibility(View.VISIBLE);
                                bookmarkVideoGalleryAdapter = new BookmarkVideoGalleryAdapter(context, videobookmarks);
                                videos_saved_recy.setAdapter(bookmarkVideoGalleryAdapter);
                            }
                            if (videobookmarks.size() == 0){
                                rel_videos_saved_items.setVisibility(View.GONE);
                            }

                            if (events.size() != 0){
                                rel_event_saved_items.setVisibility(View.VISIBLE);
                                bookmarkEventAdapter = new BookmarkEventAdapter(context,events);
                                event_saved_recy.setAdapter(bookmarkEventAdapter);
                            }
                            if (events.size() == 0){
                                rel_event_saved_items.setVisibility(View.GONE);
                            }

                            if (courses.size() != 0){
                                rel_courses_saved_items.setVisibility(View.VISIBLE);
                                bookmarkCoursesAdapter = new BookmarkCoursesAdapter(context, courses);
                                courses_saved_recy.setAdapter(bookmarkCoursesAdapter);
                            }
                            if (courses.size() == 0){
                                rel_courses_saved_items.setVisibility(View.GONE);
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onClick(View view) {

    }
}