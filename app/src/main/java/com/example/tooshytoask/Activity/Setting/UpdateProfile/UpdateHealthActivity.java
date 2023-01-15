package com.example.tooshytoask.Activity.Setting.UpdateProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.tooshytoask.Activity.Setting.UpdateProfileActivity;
import com.example.tooshytoask.Adapters.HealthAdapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.HealthIssues;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class UpdateHealthActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    SPManager spManager;
    RecyclerView health_recy, recyclerView;
    ArrayList<HealthIssues> healthIssues;
    HealthAdapter adapter;
    RelativeLayout rel_back, health;
    Button yes_btn, no_btn, next_btn;

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
        next_btn = findViewById(R.id.next_btn);
        next_btn.setOnClickListener(this);
        health = findViewById(R.id.health);
        health.setOnClickListener(this);

         /*recyclerView = view.findViewById(R.id.health_recy);
        recyclerView.setLayoutManager(new GridLayoutManager(context,2, GridLayoutManager.VERTICAL, true));

        healthIssues = new ArrayList<>();

        healthIssues.add(new HealthIssues("Throid","Mental Health"));
        healthIssues.add(new HealthIssues("Painful Periods","Irregular Periods"));
        healthIssues.add(new HealthIssues("PCOS/PCOD", "Fibroids"));

        recyclerView.setAdapter(new HealthAdapter(healthIssues));*/
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

         if (id == rel_back.getId()){

            Intent intent = new Intent(context, UpdateProfileActivity.class);
            startActivity(intent);
        } else if (id == yes_btn.getId()){
             health.setVisibility(View.VISIBLE);
             next_btn.setVisibility(View.VISIBLE);
             yes_btn.setBackgroundResource(R.drawable.gender_border_active);
             yes_btn.setTextColor(ContextCompat.getColor(context, R.color.white));
             no_btn.setBackgroundResource(R.drawable.gender_border_inactive);
             no_btn.setTextColor(ContextCompat.getColor(context, R.color.black));

         } else if (id == no_btn.getId()){
             health.setVisibility(View.GONE);
             next_btn.setVisibility(View.VISIBLE);
             no_btn.setBackgroundResource(R.drawable.gender_border_active);
             no_btn.setTextColor(ContextCompat.getColor(context, R.color.white));
             yes_btn.setBackgroundResource(R.drawable.gender_border_inactive);
             yes_btn.setTextColor(ContextCompat.getColor(context, R.color.black));

         }
    }
}