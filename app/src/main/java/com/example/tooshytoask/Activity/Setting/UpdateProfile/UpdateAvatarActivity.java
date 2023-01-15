package com.example.tooshytoask.Activity.Setting.UpdateProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.tooshytoask.Activity.Setting.UpdateProfileActivity;
import com.example.tooshytoask.Adapters.HealthAdapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.HealthIssues;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class UpdateAvatarActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    SPManager spManager;
    RelativeLayout rel_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_avatar);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = UpdateAvatarActivity.this;
        spManager = new SPManager(context);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == rel_back.getId()){

            Intent intent = new Intent(context, UpdateProfileActivity.class);
            startActivity(intent);
        }
    }
}