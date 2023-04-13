package com.neuronimbus.metropolis.activity.Blogs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.Utils.OnBookmarkClicked;
import com.neuronimbus.metropolis.activity.Home.HomeActivity;
import com.neuronimbus.metropolis.adapters.RelatedBlogAdapter;
import com.neuronimbus.metropolis.AuthModels.BlogLikeAuthModel;
import com.neuronimbus.metropolis.AuthModels.BookmarkBlogAuthModel;
import com.neuronimbus.metropolis.AuthModels.SingleBlogAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.BlogLikeResponse;
import com.neuronimbus.metropolis.Models.BookmarkBlogResponse;
import com.neuronimbus.metropolis.Models.SingleBlogResponse;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
//blog_id = Blog_id;
//        actions = action;
//        getBookmarkBlogs(action);
public class DetailEventActivity extends AppCompatActivity implements View.OnClickListener, OnBookmarkClicked {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    RelativeLayout rel_back;
    ImageView blog_img, like_courses, save_courses, share_courses, like_count_img;
    TextView txt_title, like_count, duration_time, blog_headline, blog_description;
    String event_id, type = "event", helpful ="", commentId ="", next_id ="",
            previous_id ="", islike, isBookmark, blog_id = "", actions = "";
    RecyclerView recy_blogs;
    LinearLayout previous, next, related_blog_lay;
    NestedScrollView event_scroll_view;
    boolean like = true;
    RelatedBlogAdapter adapter;
    ArrayList<com.neuronimbus.metropolis.Models.relatedblogs> relatedblogs;
    ArrayList<com.neuronimbus.metropolis.Models.singleblog> singleblog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = DetailEventActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        event_scroll_view = findViewById(R.id.event_scroll_view);
        related_blog_lay = findViewById(R.id.related_blog_lay);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        blog_headline = findViewById(R.id.blog_headline);
        blog_description = findViewById(R.id.blog_description);
        blog_img = findViewById(R.id.blog_img);
        txt_title = findViewById(R.id.txt_title);
        like_count = findViewById(R.id.like_count);
        duration_time = findViewById(R.id.duration_time);
        like_courses = findViewById(R.id.like_courses);
        like_courses.setOnClickListener(this);
        save_courses = findViewById(R.id.save_courses);
        save_courses.setOnClickListener(this);
        share_courses = findViewById(R.id.share_courses);
        share_courses.setOnClickListener(this);
        previous = findViewById(R.id.previous);
        previous.setOnClickListener(this);
        next = findViewById(R.id.next);
        next.setOnClickListener(this);

        recy_blogs = findViewById(R.id.recy_blogs);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recy_blogs.setLayoutManager(linearLayoutManager);

        event_id = getIntent().getStringExtra("blog_id");

        getSingleBlog();

    }

    public void blogLike(String type){

        BlogLikeAuthModel model = new BlogLikeAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setPost_id(event_id);
        model.setType(type);
        model.setIshelpfull(helpful);

        WebServiceModel.getRestApi().blogLike(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<BlogLikeResponse>() {
                    @Override
                    public void onNext(BlogLikeResponse blogLikeResponse) {
                        String msg = blogLikeResponse.getMsg();

                        if (msg.equals("Blog Liked")){

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

    public void getBookmarkBlogs(String action) {

        BookmarkBlogAuthModel model = new BookmarkBlogAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setPost_id(event_id);
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

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getSingleBlog() {
        dialog.show("");
        event_scroll_view.setVisibility(View.GONE);

        SingleBlogAuthModel model = new SingleBlogAuthModel();
        model.setPost_id(event_id);
        model.setUser_id(spManager.getUserId());

        WebServiceModel.getRestApi().getSingleBlog(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<SingleBlogResponse>() {
                    @Override
                    public void onNext(SingleBlogResponse singleBlogResponse) {
                        String msg = singleBlogResponse.getMsg();
                        if (msg.equals("success")) {
                            singleblog = singleBlogResponse.getSingleblog();
                            relatedblogs = singleBlogResponse.getRelatedblogs();

                            txt_title.setText(Html.fromHtml(singleblog.get(0).getBlog_title()));
                            blog_headline.setText(Html.fromHtml(singleblog.get(0).getBlog_title()));
                            Glide.with(context).load(singleblog.get(0).getBlog_img()).into(blog_img);
                            blog_description.setText(Html.fromHtml(singleblog.get(0).getBlog_content()));
                            blog_description.setMovementMethod(LinkMovementMethod.getInstance());
                            like_count.setText(Html.fromHtml(singleblog.get(0).getBlog_like()));
                            next_id = singleBlogResponse.getNextblog();
                            previous_id = singleBlogResponse.getPreviousblog();

                            islike = singleblog.get(0).getBlog_liked();
                            isBookmark = singleblog.get(0).getBlog_bookmarked();

                            setIslike();

                            if (next_id.equals(next_id)) {
                                next.setVisibility(View.VISIBLE);

                            }
                            if (next_id.equals("0")){
                                next.setVisibility(View.GONE);
                            }

                            if (previous_id.equals(previous_id)) {
                                previous.setVisibility(View.VISIBLE);

                            }
                            if (previous_id.equals("0")){
                                previous.setVisibility(View.GONE);
                            }

                            if (relatedblogs.size() != 0) {
                                related_blog_lay.setVisibility(View.VISIBLE);
                                adapter = new RelatedBlogAdapter(context, relatedblogs, DetailEventActivity.this);
                                recy_blogs.setAdapter(adapter);
                            }

                            if (relatedblogs.size() == 0) {
                                related_blog_lay.setVisibility(View.GONE);

                            }

                        }
                        dialog.dismiss("");
                        event_scroll_view.setVisibility(View.VISIBLE);
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

    public void setIslike(){

        if (islike.equals("1")) {
            like_courses.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.like_active));
            like = false;

        } else {
            like_courses.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.like));
            like = true;
        }

        if (isBookmark.equals("1")) {
            save_courses.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.filled));
            like = false;

        } else {
            save_courses.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.saved));
            like = true;
        }
    }

    public void singleBlog() {

        SingleBlogAuthModel model = new SingleBlogAuthModel();
        model.setPost_id(event_id);
        model.setUser_id(spManager.getUserId());

        WebServiceModel.getRestApi().getSingleBlog(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<SingleBlogResponse>() {
                    @Override
                    public void onNext(SingleBlogResponse singleBlogResponse) {
                        String msg = singleBlogResponse.getMsg();
                        if (msg.equals("success")) {
                            singleblog = singleBlogResponse.getSingleblog();

                            like_count.setText(Html.fromHtml(singleblog.get(0).getBlog_like()));


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

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == save_courses.getId()) {
            if (like) {
                save_courses.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.filled));
                getBookmarkBlogs("save");
                like = false;
            } else {
                save_courses.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.saved));
                getBookmarkBlogs("remove");
                like = true;
            }

        }
        else if (id == like_courses.getId()) {
            if (like) {
                like_courses.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.like_active));
                blogLike("like");
                dialog.dismiss("");
                singleBlog();
                like = false;
            } else {
                like_courses.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.like));
                blogLike("like");
                dialog.dismiss("");
                singleBlog();
                like = true;
            }
        }

        else if (id  == share_courses.getId()){
            sharevalue();
        }
        else if (id == previous.getId()){

            Intent intent = new Intent(context, DetailBlogActivity.class);
            intent.putExtra("blog_id", previous_id);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
        else if (id == next.getId()){

            Intent intent = new Intent(context, DetailBlogActivity.class);
            intent.putExtra("blog_id", next_id);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
        else if (id == rel_back.getId()){
            Intent intent = new Intent(context, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }

    public void sharevalue() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Too Shy Too Ask App");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, singleblog.get(0).getBlog_title() + "\n\n" + singleblog.get(0).getBlog_link());
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    @Override
    public void onBookmarkButtonClick(int position, String Blog_id, String action) {
        blog_id = Blog_id;
        actions = action;
        getBookmarkBlogs(action);
    }
}