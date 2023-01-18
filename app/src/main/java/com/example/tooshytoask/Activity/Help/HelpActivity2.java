package com.example.tooshytoask.Activity.Help;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.tooshytoask.Adapters.Help2Adapter;
import com.example.tooshytoask.Adapters.Help3Adapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.Help2Item;
import com.example.tooshytoask.Models.Help3Item;
import com.example.tooshytoask.R;

import java.util.ArrayList;

public class HelpActivity2 extends AppCompatActivity implements View.OnClickListener{
    RecyclerView using_tsta_recy, content_related_recy;
    SPManager spManager;
    Context context;
    Help2Adapter adapter;
    Help3Adapter adapter1;
    ArrayList<Help2Item>help2Item;
    ArrayList<Help3Item>help3Item;
    RelativeLayout rel_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help2);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = HelpActivity2.this;
        spManager = new SPManager(context);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        using_tsta_recy = findViewById(R.id.using_tsta_recy);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        using_tsta_recy.setLayoutManager(linearLayoutManager);

        help2Item = new ArrayList<>();

        help2Item.add(new Help2Item(R.drawable.update_arrow,"Suspendisse condimentum nisl vitae tellus?"));
        help2Item.add(new Help2Item(R.drawable.update_arrow,"Morbi blandit elit et urna placerat?"));
        help2Item.add(new Help2Item(R.drawable.update_arrow,"Sed auctor justo id dictum sodales.?"));
        help2Item.add(new Help2Item(R.drawable.update_arrow,"Suspendisse ut leo at libero cursus iaculis.?"));
        help2Item.add(new Help2Item(R.drawable.update_arrow,"Cras vitae diam eu quam tincidunt semper.?"));
        help2Item.add(new Help2Item(R.drawable.update_arrow,"Suspendisse condimentum nisl vitae tellus?"));
        help2Item.add(new Help2Item(R.drawable.update_arrow,"Mauris fringilla justo in arcu facilisis?"));

        using_tsta_recy.setAdapter(new Help2Adapter(help2Item, context));

        content_related_recy = findViewById(R.id.content_related_recy);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        content_related_recy.setLayoutManager(linearLayoutManager1);

        help3Item = new ArrayList<>();

        help3Item.add(new Help3Item(R.drawable.update_arrow,"Suspendisse condimentum nisl vitae tellus?"));
        help3Item.add(new Help3Item(R.drawable.update_arrow,"Morbi blandit elit et urna placerat?"));
        help3Item.add(new Help3Item(R.drawable.update_arrow,"Sed auctor justo id dictum sodales.?"));
        help3Item.add(new Help3Item(R.drawable.update_arrow,"Suspendisse ut leo at libero cursus iaculis.?"));
        help3Item.add(new Help3Item(R.drawable.update_arrow,"Cras vitae diam eu quam tincidunt semper.?"));
        help3Item.add(new Help3Item(R.drawable.update_arrow,"Suspendisse condimentum nisl vitae tellus?"));
        help3Item.add(new Help3Item(R.drawable.update_arrow,"Mauris fringilla justo in arcu facilisis?"));

        content_related_recy.setAdapter(new Help2Adapter(help2Item, context));
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == rel_back.getId()){
            Intent intent = new Intent(context, HelpActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }
}