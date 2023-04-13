package com.neuronimbus.metropolis.activity.LMS.LMSQuiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;

public class LMSQuizMainActivity extends AppCompatActivity {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    Button btn_start;
    String courses_id ="",lesson_id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lmsquiz_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = LMSQuizMainActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);
        btn_start = findViewById(R.id.btn_start);

        lesson_id = getIntent().getStringExtra("lesson_id");
        courses_id = getIntent().getStringExtra("courses_id");

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LMSQuizActivity.class);
                intent.putExtra("lesson_id", lesson_id);
                intent.putExtra("courses_id", courses_id);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}