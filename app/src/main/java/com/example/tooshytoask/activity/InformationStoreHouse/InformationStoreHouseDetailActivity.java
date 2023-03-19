package com.example.tooshytoask.activity.InformationStoreHouse;

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
import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.activity.Blogs.DetailBlogActivity;
import com.example.tooshytoask.activity.Home.HomeActivity;
import com.example.tooshytoask.adapters.RelatedStoreHouseAdapter;
import com.example.tooshytoask.AuthModels.BookmarkBlogAuthModel;
import com.example.tooshytoask.AuthModels.StoreHouseLikeAuthModel;
import com.example.tooshytoask.AuthModels.StoreHouseRelatedAuthModel;
import com.example.tooshytoask.AuthModels.StoreHouseSinglePageAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.BookmarkBlogResponse;
import com.example.tooshytoask.Models.StoreHouseLikeResponse;
import com.example.tooshytoask.Models.StoreHouseRelatedResponse;
import com.example.tooshytoask.Models.StoreHouseSinglePageResponse;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.example.tooshytoask.Utils.OnBookmarkClicked;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class InformationStoreHouseDetailActivity extends AppCompatActivity implements View.OnClickListener, OnBookmarkClicked {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    String article_id, next_id ="", previous_id ="", type = "storehouse", islike, isBookmark;
    TextView yes_count, no_count, txt_title, like_count, duration_time, blog_headline, blog_description, helpful;
    ImageView blog_img, like_courses, save_courses, share_courses,like_count_img;
    LinearLayout previous, next, helpful_yes, helpful_no, related_blog_lay;
    RelativeLayout rel_back;
    RelatedStoreHouseAdapter adapter;
    ArrayList<com.example.tooshytoask.Models.storehousedata> storehousedata;
    ArrayList<com.example.tooshytoask.Models.relatedstorehouse>relatedstorehouse;
    boolean like = true;
    RecyclerView recy_storehouse;
    NestedScrollView storehouse_scroll_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_store_house_detail);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = InformationStoreHouseDetailActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        storehouse_scroll_view = findViewById(R.id.storehouse_scroll_view);
        related_blog_lay = findViewById(R.id.related_blog_lay);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        helpful_no = findViewById(R.id.helpful_no);
        helpful_no.setOnClickListener(this);
        helpful_yes = findViewById(R.id.helpful_yes);
        helpful_yes.setOnClickListener(this);
        blog_headline = findViewById(R.id.blog_headline);
        blog_description = findViewById(R.id.blog_description);
        yes_count = findViewById(R.id.yes_count);
        no_count = findViewById(R.id.no_count);
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

        recy_storehouse = findViewById(R.id.recy_storehouse);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recy_storehouse.setLayoutManager(linearLayoutManager);

        article_id = getIntent().getStringExtra("article_id");

        getStoreHouseSinglePage();
        getStoreHouseRelated();
    }

    public void getStoreHouseSinglePage(){
        dialog.show("");
        storehouse_scroll_view.setVisibility(View.GONE);

        StoreHouseSinglePageAuthModel model = new StoreHouseSinglePageAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setArticle_id(article_id);

        WebServiceModel.getRestApi().getStoreHouseSinglePage(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<StoreHouseSinglePageResponse>() {
                    @Override
                    public void onNext(StoreHouseSinglePageResponse storeHouseSinglePageResponse) {
                        String msg = storeHouseSinglePageResponse.getMsg();
                        dialog.dismiss("");
                        storehouse_scroll_view.setVisibility(View.VISIBLE);
                        if (msg.equals("success")){
                            storehousedata = storeHouseSinglePageResponse.getStorehousedata();

                            txt_title.setText(Html.fromHtml(storehousedata.get(0).getArticle_name()));
                            blog_headline.setText(Html.fromHtml(storehousedata.get(0).getArticle_name()));
                            Glide.with(context).load(storehousedata.get(0).getArticle_image()).into(blog_img);
                            blog_description.setText(Html.fromHtml(storehousedata.get(0).getArticle_description()));
                            blog_description.setMovementMethod(LinkMovementMethod.getInstance());
                            duration_time.setText(Html.fromHtml(storehousedata.get(0).getReadTime()));
                            like_count.setText(Html.fromHtml(storehousedata.get(0).getLikeCnt()));
                            yes_count.setText(Html.fromHtml(storehousedata.get(0).getHelpfull()));
                            no_count.setText(Html.fromHtml(storehousedata.get(0).getNothelpfull_cnt()));
                            next_id = storehousedata.get(0).getNextarticleId();
                            previous_id = storehousedata.get(0).getPreviousarticleId();

                            islike = storehousedata.get(0).getLiked();
                            isBookmark = storehousedata.get(0).getBookmarked();

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

    public void getSinglePage(){

        StoreHouseSinglePageAuthModel model = new StoreHouseSinglePageAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setArticle_id(article_id);

        WebServiceModel.getRestApi().getStoreHouseSinglePage(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<StoreHouseSinglePageResponse>() {
                    @Override
                    public void onNext(StoreHouseSinglePageResponse storeHouseSinglePageResponse) {
                        String msg = storeHouseSinglePageResponse.getMsg();
                        if (msg.equals("success")){
                            storehousedata = storeHouseSinglePageResponse.getStorehousedata();

                            like_count.setText(Html.fromHtml(storehousedata.get(0).getLikeCnt()));
                            yes_count.setText(Html.fromHtml(storehousedata.get(0).getHelpfull()));
                            no_count.setText(Html.fromHtml(storehousedata.get(0).getNothelpfull_cnt()));


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

    public void getStoreHouseLike(String type){
        //dialog.show("");

        StoreHouseLikeAuthModel model = new StoreHouseLikeAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setArticle_id(article_id);
        model.setType(type);

        WebServiceModel.getRestApi().getStoreHouseLike(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<StoreHouseLikeResponse>() {
                    @Override
                    public void onNext(StoreHouseLikeResponse storeHouseLikeResponse) {
                        String msg = storeHouseLikeResponse.getMsg();
                        dialog.dismiss("");

                        if (msg.equals("success")){

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
        //dialog.show("");

        BookmarkBlogAuthModel model = new BookmarkBlogAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setPost_id(article_id);
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

    public void getStoreHouseRelated(){

        StoreHouseRelatedAuthModel model = new StoreHouseRelatedAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setArticle_id(article_id);

        WebServiceModel.getRestApi().getStoreHouseRelated(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<StoreHouseRelatedResponse>() {
                    @Override
                    public void onNext(StoreHouseRelatedResponse storeHouseRelatedResponse) {
                        String msg = storeHouseRelatedResponse.getMsg();

                        if (msg.equals("success")){
                            relatedstorehouse = storeHouseRelatedResponse.getRelatedstorehouse();



                            if (relatedstorehouse.size() != 0) {
                                related_blog_lay.setVisibility(View.VISIBLE);
                                adapter = new RelatedStoreHouseAdapter(context, relatedstorehouse, InformationStoreHouseDetailActivity.this);
                                recy_storehouse.setAdapter(adapter);
                            }

                            if (relatedstorehouse.size() == 0) {
                                related_blog_lay.setVisibility(View.GONE);

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
                getStoreHouseLike("like");
                getSinglePage();
                like = false;
            } else {
                like_courses.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.like));
                getStoreHouseLike("unlike");
                getSinglePage();
                like = true;
            }

        }
        else if (id  == helpful_yes.getId()){
            getStoreHouseLike("helpfull");
            getSinglePage();
        }
        else if (id  == helpful_no.getId()){
            getStoreHouseLike("nothelpfull");
            getSinglePage();
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
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, storehousedata.get(0).getArticle_name() + "\n\n" + storehousedata.get(0).getShareUrl());
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    @Override
    public void onBookmarkButtonClick(int position, String Blog_id, String action) {

    }
}