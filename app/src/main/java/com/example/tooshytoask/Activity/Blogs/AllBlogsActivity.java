package com.example.tooshytoask.Activity.Blogs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.tooshytoask.Activity.Landing.SignUpActivity;
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
    BlogAdapter adapter2;
    ArrayList<BlogItems> blogItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_blogs);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = AllBlogsActivity.this;
        spManager = new SPManager(context);

        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
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

        adapter2 = new BlogAdapter(context ,blogItems);
        blog_recy.setAdapter(adapter2);


    }

    @Override
    public void onClick(View view) {

    }
}