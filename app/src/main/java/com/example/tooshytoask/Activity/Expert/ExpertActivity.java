package com.example.tooshytoask.Activity.Expert;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;

public class ExpertActivity extends AppCompatActivity implements View.OnClickListener{
    SPManager spManager;
    Context context;
    CustomProgressDialog dialog;
    RelativeLayout rel_back;
    RecyclerView recy_user_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expert);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = ExpertActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);

        recy_user_msg = findViewById(R.id.recy_user_msg);
        LinearLayoutManager lm = new LinearLayoutManager(context);
        lm.setOrientation(RecyclerView.VERTICAL);
        recy_user_msg.setLayoutManager(lm);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == rel_back.getId()){
            finish();
        }

    }
}