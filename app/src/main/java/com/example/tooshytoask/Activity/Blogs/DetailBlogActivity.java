package com.example.tooshytoask.Activity.Blogs;

import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Adapters.DetailBlogAdapter;
import com.example.tooshytoask.AuthModels.SingleBlogAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.SingleBlogResponse;
import com.example.tooshytoask.Models.singleblog;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class DetailBlogActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    String blog_id;
    ArrayList<singleblog> singleblog;
    TextView yes_count, no_count, txt_title, like_count, duration_time, blog_headline, blog_description;
    ImageView blog_img, like_courses, save_courses, share_courses;
    LinearLayout previous, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_blog);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = DetailBlogActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

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

        Intent intent = getIntent();
        if (intent != null) {

            blog_id = intent.getStringExtra("blog_id");

        }
        getSingleBlog();

    }

    public void getSingleBlog(){
        dialog.show("");
        dialog.dismiss("");

        SingleBlogAuthModel model = new SingleBlogAuthModel();
        model.setPost_id(blog_id);

        WebServiceModel.getRestApi().getSingleBlog(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<SingleBlogResponse>() {
                    @Override
                    public void onNext(SingleBlogResponse singleBlogResponse) {
                        String msg = singleBlogResponse.getMsg();
                        if (msg.equals("success")){
                            singleblog = singleBlogResponse.getSingleblog();

                            for (int i=0; i<singleblog.size(); i++ ){

                                txt_title.setText(Html.fromHtml(singleblog.get(i).getBlog_title()));
                                blog_headline.setText(Html.fromHtml(singleblog.get(i).getBlog_title()));
                                Glide.with(context).load(singleblog.get(i).getBlog_img()).into(blog_img);
                                blog_description.setText(Html.fromHtml(singleblog.get(i).getBlog_content()));
                                blog_description.setMovementMethod(LinkMovementMethod.getInstance());

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
    public void onClick(View v) {

    }
}