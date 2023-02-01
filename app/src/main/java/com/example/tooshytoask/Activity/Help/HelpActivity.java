package com.example.tooshytoask.Activity.Help;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Adapters.HelpCategoryAdapter;
import com.example.tooshytoask.Adapters.ProfileAdapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.AvatarResponse;
import com.example.tooshytoask.Models.Help.HelpCategoryResponse;
import com.example.tooshytoask.Models.Help.data;
import com.example.tooshytoask.Models.HelpCategory;
import com.example.tooshytoask.R;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener{
    RecyclerView help_category_recy, recyclerView;
    SPManager spManager;
    Context context;
    ArrayList<HelpCategory>helpCategories;
    ArrayList<com.example.tooshytoask.Models.Help.data> data;
    HelpCategoryAdapter adapter;
    RelativeLayout rel_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = HelpActivity.this;
        spManager = new SPManager(context);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        help_category_recy = findViewById(R.id.help_category_recy);
        help_category_recy.setLayoutManager(new GridLayoutManager(context,3, GridLayoutManager.VERTICAL, false));

        /*helpCategories = new ArrayList<>();

        helpCategories.add(new HelpCategory(R.drawable.using_tsta,"Using TSTA"));
        helpCategories.add(new HelpCategory(R.drawable.acccount, "Account"));
        helpCategories.add(new HelpCategory(R.drawable.general, "General"));
        helpCategories.add(new HelpCategory(R.drawable.privacy, "Privacy & Security"));
        helpCategories.add(new HelpCategory(R.drawable.troubleshooting, "Troubleshooting"));
        helpCategories.add(new HelpCategory(R.drawable.others, "Others"));

        help_category_recy.setAdapter(new HelpCategoryAdapter(context, helpCategories));*/
        getHelpCategory();

    }
    public void getHelpCategory() {

        WebServiceModel.getRestApi().getHelpCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<HelpCategoryResponse>() {
                    @Override
                    public void onNext(HelpCategoryResponse helpCategoryResponse) {

                        String msg = helpCategoryResponse.getMsg();

                        if (msg.equals("success")) {

                            data = helpCategoryResponse.getData();

                            if (data != null) {
                                adapter = new HelpCategoryAdapter(context, data);
                                help_category_recy.setAdapter(adapter);
                            }
                        } else {

                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                        Toast.makeText(context, "Please Check Your Network..Unable to Connect Server!!", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == rel_back.getId()){
            finish();
        }
    }
}