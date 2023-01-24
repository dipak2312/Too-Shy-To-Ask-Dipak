package com.example.tooshytoask.Activity.Setting.UpdateProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.tooshytoask.Activity.Setting.UpdateProfileActivity;
import com.example.tooshytoask.Adapters.UpdateHealthAdapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.HealthIssue;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.OnClickListner;

import java.util.ArrayList;

public class UpdateHealthActivity extends AppCompatActivity implements View.OnClickListener, OnClickListner{
    Context context;
    SPManager spManager;
    RecyclerView health_recy, recyclerView;
    ArrayList<HealthIssue> healthIssues;
    UpdateHealthAdapter adapter;
    RelativeLayout rel_back, health;
    Button yes_btn, no_btn, update_btn2;
    OnClickListner onclicklistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_health);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = UpdateHealthActivity.this;
        spManager = new SPManager(context);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        yes_btn = findViewById(R.id.yes_btn);
        yes_btn.setOnClickListener(this);
        no_btn = findViewById(R.id.no_btn);
        no_btn.setOnClickListener(this);
        update_btn2 = findViewById(R.id.update_btn2);
        update_btn2.setOnClickListener(this);
        health = findViewById(R.id.health);
        health.setOnClickListener(this);

        health_recy = findViewById(R.id.health_recy);
        //health_recy.setLayoutManager(new GridLayoutManager(context,2, GridLayoutManager.VERTICAL, true));

        healthIssues = new ArrayList<>();

        healthIssues.add(new HealthIssue("Throid", false));
        healthIssues.add(new HealthIssue("Painful Periods", false));
        healthIssues.add(new HealthIssue("Mental Health", false));
        healthIssues.add(new HealthIssue("Irregular Periods", false));
        healthIssues.add(new HealthIssue("Fibroids", false));
        healthIssues.add(new HealthIssue("PCOS/PCOD", false));

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        health_recy.setLayoutManager(linearLayoutManager1);


        health_recy.setAdapter(new UpdateHealthAdapter(healthIssues,onclicklistener, context));
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

         if (id == rel_back.getId()){
            Intent intent = new Intent(context, UpdateProfileActivity.class);
            startActivity(intent);
        }
         else if (id == update_btn2.getId()){
             Intent intent = new Intent(context, UpdateProfileActivity.class);
             startActivity(intent);
         }
         else if (id == yes_btn.getId()){
             health.setVisibility(View.VISIBLE);
             yes_btn.setBackgroundResource(R.drawable.gender_border_active);
             yes_btn.setTextColor(ContextCompat.getColor(context, R.color.white));
             no_btn.setBackgroundResource(R.drawable.gender_border_inactive);
             no_btn.setTextColor(ContextCompat.getColor(context, R.color.black));

         } else if (id == no_btn.getId()){
             health.setVisibility(View.GONE);
             no_btn.setBackgroundResource(R.drawable.gender_border_active);
             no_btn.setTextColor(ContextCompat.getColor(context, R.color.white));
             yes_btn.setBackgroundResource(R.drawable.gender_border_inactive);
             yes_btn.setTextColor(ContextCompat.getColor(context, R.color.black));

         }
    }

    @Override
    public void onClickData(int position, int id) {

    }
}