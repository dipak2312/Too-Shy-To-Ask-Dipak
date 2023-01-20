package com.example.tooshytoask.Activity.Blogs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.tooshytoask.Activity.Bookmark.BookmarkActivity;
import com.example.tooshytoask.Activity.Landing.OtpVerificationActivity;
import com.example.tooshytoask.Activity.Landing.SignUpActivity;
import com.example.tooshytoask.Adapters.AllBlogAdapter;
import com.example.tooshytoask.Adapters.BlogAdapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.BlogItems;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class AllBlogsActivity extends AppCompatActivity implements View.OnClickListener{
    RecyclerView blog_recy;
    SPManager spManager;
    Context context;
    RelativeLayout rel_back;
    AllBlogAdapter adapter2;
    ArrayList<BlogItems> blogItems;
    ImageView bookmark_blog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_blogs);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = AllBlogsActivity.this;
        spManager = new SPManager(context);

        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        bookmark_blog = findViewById(R.id.bookmark_blog);
        bookmark_blog.setOnClickListener(this);
        blog_recy = findViewById(R.id.blog_recy);

        blogItems = new ArrayList<>();

        blogItems.add(new BlogItems(R.drawable.blog2, R.drawable.save, "Obesity – much more than a cosmetic..."));
        blogItems.add(new BlogItems(R.drawable.blog1, R.drawable.save, "Dominance Of Partner – Controlling..."));
        blogItems.add(new BlogItems(R.drawable.blog3, R.drawable.save, "Obesity – much more than a cosmetic..."));
        blogItems.add(new BlogItems(R.drawable.blog2, R.drawable.save, "Dominance Of Partner – Controlling..."));
        blogItems.add(new BlogItems(R.drawable.blog2, R.drawable.save, "Obesity – much more than a cosmetic..."));
        blogItems.add(new BlogItems(R.drawable.blog1, R.drawable.save, "Dominance Of Partner – Controlling..."));
        blogItems.add(new BlogItems(R.drawable.blog3, R.drawable.save, "Obesity – much more than a cosmetic..."));
        blogItems.add(new BlogItems(R.drawable.blog2, R.drawable.save, "Dominance Of Partner – Controlling..."));

        blog_recy.setLayoutManager(new GridLayoutManager(context,2, GridLayoutManager.VERTICAL, false));

        adapter2 = new AllBlogAdapter(context ,blogItems);
        blog_recy.setAdapter(adapter2);


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
}