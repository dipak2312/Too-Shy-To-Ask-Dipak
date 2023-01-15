package com.example.tooshytoask.Activity.Search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.R;

public class SearchActivity extends AppCompatActivity {
    Context context;
    SPManager spManager;
    EditText edit_search;
    RecyclerView recy_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = SearchActivity.this;
        spManager = new SPManager(context);
    }
}