package com.example.tooshytoask.Activity.Courses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tooshytoask.Adapters.RecentlyBlogAdapter;
import com.example.tooshytoask.R;

public class AllCoursesActivity extends AppCompatActivity {
    RecyclerView courses_recy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_courses);
    }
}