package com.example.tooshytoask.Activity.Help;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;

public class Help3Activity extends AppCompatActivity implements View.OnClickListener{
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    Button contact_us;
    RelativeLayout rel_back;
    TextView thanku_msg;
    Button yes, no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help3);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = Help3Activity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);
        contact_us = findViewById(R.id.contact_us);
        contact_us.setOnClickListener(this);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        thanku_msg = findViewById(R.id.thanku_msg);
        thanku_msg.setOnClickListener(this);
        yes = findViewById(R.id.yes);
        yes.setOnClickListener(this);
        no = findViewById(R.id.no);
        no.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == contact_us.getId()){
            Intent intent = new Intent(context, ContactUsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        else if (id == rel_back.getId()){
            Intent intent = new Intent(context, HelpActivity2.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        else if (id == yes.getId()){
            thanku_msg.setVisibility(View.VISIBLE);
            yes.setBackgroundResource(R.drawable.gender_border_active);
            yes.setTextColor(ContextCompat.getColor(context, R.color.white));
        }
        else if (id == no.getId()){
            Intent intent = new Intent(context, HelpActivity2.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

    }
}