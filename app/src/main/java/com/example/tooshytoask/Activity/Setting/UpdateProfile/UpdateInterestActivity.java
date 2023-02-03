package com.example.tooshytoask.Activity.Setting.UpdateProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tooshytoask.Activity.Setting.UpdateProfileActivity;
import com.example.tooshytoask.Adapters.CategoryAdapter;
import com.example.tooshytoask.Adapters.UpdateCategoryAdapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.CategoryItem;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.ClickListener;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.example.tooshytoask.Utils.OnClickListner;

import java.util.ArrayList;

public class UpdateInterestActivity extends AppCompatActivity implements View.OnClickListener, OnClickListner{
    Context context;
    SPManager spManager;
    RecyclerView category_recy;
    UpdateCategoryAdapter adapter;
    ArrayList<CategoryItem> categoryItem;
    RelativeLayout rel_back;
    CustomProgressDialog dialog;
    Button update_btn;
    ClickListener clickListener;
    OnClickListner onclicklistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_interest);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = UpdateInterestActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);
        /*clickListener=(ClickListener)context;
        clickListener.onClick(false);*/
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        update_btn = findViewById(R.id.update_btn);
        update_btn.setOnClickListener(this);
        category_recy = findViewById(R.id.category_recy);

        category_recy.setLayoutManager(new GridLayoutManager(context,3, GridLayoutManager.VERTICAL, false));

        categoryItem = new ArrayList<>();

        categoryItem.add(new CategoryItem(R.drawable.reproduction,"Relationships",false));
        categoryItem.add(new CategoryItem(R.drawable.mental_health,"Sex & Sexuality",false));
        categoryItem.add(new CategoryItem(R.drawable.reproduction,"Reproduction",false));
        categoryItem.add(new CategoryItem(R.drawable.mental_health,"Mental Health",false));
        categoryItem.add(new CategoryItem(R.drawable.reproduction,"Education",false));
        categoryItem.add(new CategoryItem(R.drawable.mental_health,"Sexual Assault",false));

        category_recy.setAdapter(new UpdateCategoryAdapter(categoryItem,onclicklistener, clickListener));
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == rel_back.getId()){

            Intent intent = new Intent(context, UpdateProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            //clickListener.onClick(true);
        }
        if (id == update_btn.getId()){

            Intent intent = new Intent(context, UpdateProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            dialog.show("");
            dialog.dismiss("");
        }
        /*ArrayList<Boolean> myvalue=new ArrayList<Boolean>();

        for(int i=0;i<categoryItem.size();i++)
        {
            myvalue.add(categoryItem.get(i).getSelected());
        }
        boolean ans = myvalue.contains(true);

        if(ans)
        {
            clickListener.onClick(true);


        }else
        {
            clickListener.onClick(false);
        }*/


    }

    @Override
    public void onClickData(int position, String id) {
        /*ArrayList<Boolean> myvalue=new ArrayList<Boolean>();

        for(int i=0;i<categoryItem.size();i++)
        {
            myvalue.add(categoryItem.get(i).getSelected());
        }

        boolean ans = myvalue.contains(true);

        if(ans)
        {
            update_btn.setBackgroundResource(R.drawable.circle_button_active);


        }else
        {
            update_btn.setBackgroundResource(R.drawable.circle_button_inactive);
        }*/
    }


}
