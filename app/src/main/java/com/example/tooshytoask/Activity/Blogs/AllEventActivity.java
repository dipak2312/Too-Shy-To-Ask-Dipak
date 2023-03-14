package com.example.tooshytoask.Activity.Blogs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Activity.Bookmark.BookmarkActivity;
import com.example.tooshytoask.Adapters.AllEventsAdapter;
import com.example.tooshytoask.AuthModels.AllEventAuthModel;
import com.example.tooshytoask.AuthModels.BookmarkBlogAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.AllEventResponse;
import com.example.tooshytoask.Models.BookmarkBlogResponse;
import com.example.tooshytoask.Models.insightevents;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.example.tooshytoask.Utils.OnBookmarkClicked;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class AllEventActivity extends AppCompatActivity implements View.OnClickListener, OnBookmarkClicked {
    RecyclerView events_recy;
    SPManager spManager;
    Context context;
    ImageView bookmark_blog;
    RelativeLayout rel_back;
    CustomProgressDialog dialog;
    ArrayList<insightevents> insightevents;
    AllEventsAdapter adapter;
    String blog_id = "", type = "event", actions = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_event);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = AllEventActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        bookmark_blog = findViewById(R.id.bookmark_blog);
        bookmark_blog.setOnClickListener(this);
        events_recy = findViewById(R.id.events_recy);

        events_recy.setLayoutManager(new GridLayoutManager(context,2, GridLayoutManager.VERTICAL, false));

        getEventBlogs();
    }

    public void getBookmarkBlogs(String action){
        dialog.show("");
        dialog.dismiss("");

        BookmarkBlogAuthModel model = new BookmarkBlogAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setPost_id(blog_id);
        model.setType(type);
        model.setAction(action);

        WebServiceModel.getRestApi().getBookmarkBlogs(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<BookmarkBlogResponse>() {
                    @Override
                    public void onNext(BookmarkBlogResponse bookmarkBlogResponse) {
                        String msg = bookmarkBlogResponse.getMsg();
                        dialog.dismiss("");

                        if (msg.equals("Article Bookmarked")) {

                        }
                        else {
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getEventBlogs(){
        dialog.show("");

        AllEventAuthModel model = new AllEventAuthModel();
        model.setUser_id(spManager.getUserId());

        WebServiceModel.getRestApi().getEventBlogs(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<AllEventResponse>() {
                    @Override
                    public void onNext(AllEventResponse allEventResponse) {
                        String msg = allEventResponse.getMsg();
                        dialog.dismiss("");

                        if (msg.equals("success")){
                            insightevents = allEventResponse.getInsightevents();

                            adapter = new AllEventsAdapter(context, insightevents, AllEventActivity.this);
                            events_recy.setAdapter(adapter);
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
        int id = view.getId();

        if (id == rel_back.getId()) {
            finish();
        }
        else if (id == bookmark_blog.getId()) {
            Intent intent = new Intent(context, BookmarkActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onBookmarkButtonClick(int position, String Blog_id, String action) {
        blog_id = Blog_id;
        actions = action;
        getBookmarkBlogs(action);
    }
}