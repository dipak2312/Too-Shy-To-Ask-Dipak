package com.example.tooshytoask.Activity.Bookmark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.service.autofill.Validator;
import android.view.View;

import com.example.tooshytoask.Activity.Blogs.AllBlogsActivity;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.R;

public class BookmarkActivity extends AppCompatActivity implements View.OnClickListener {
    SPManager spManager;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = BookmarkActivity.this;
        spManager = new SPManager(context);
    }

    @Override
    public void onClick(View view) {

    }
}