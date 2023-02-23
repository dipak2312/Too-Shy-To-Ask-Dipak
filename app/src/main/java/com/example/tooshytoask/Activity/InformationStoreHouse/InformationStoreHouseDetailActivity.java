package com.example.tooshytoask.Activity.InformationStoreHouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tooshytoask.Activity.Blogs.DetailBlogActivity;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;

public class InformationStoreHouseDetailActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    String blog_id;
    TextView yes_count, no_count, txt_title, like_count, duration_time, blog_headline, blog_description;
    ImageView blog_img, like_courses, save_courses, share_courses,like_count_img;
    LinearLayout previous, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_store_house_detail);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = InformationStoreHouseDetailActivity.this;
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
    }

    @Override
    public void onClick(View v) {

    }
}