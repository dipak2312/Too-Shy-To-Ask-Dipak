package com.neuronimbus.metropolis.activity.Help;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.Utils.LocaleHelper;
import com.neuronimbus.metropolis.adapters.Help2Adapter;
import com.neuronimbus.metropolis.AuthModels.HelpSubCategoryAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.Help.HelpSubCategoryResponse;
import com.neuronimbus.metropolis.Models.Help.helpsubcategory;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class HelpActivity2 extends AppCompatActivity implements View.OnClickListener{
    RecyclerView using_tsta_recy;
    SPManager spManager;
    Context context;
    Help2Adapter help2Adapter;
    ArrayList<helpsubcategory>helpsubcategory;
    RelativeLayout rel_back;
    Button contact;
    CustomProgressDialog dialog;
    TextView txt_title;
    String category_id = "", helpcontent_catid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help2);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = HelpActivity2.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        txt_title = findViewById(R.id.txt_title);

        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        using_tsta_recy = findViewById(R.id.using_tsta_recy);
        contact = findViewById(R.id.contact);
        contact.setOnClickListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        using_tsta_recy.setLayoutManager(linearLayoutManager);

        Intent intent1 = getIntent();
        if (intent1 != null) {

            helpcontent_catid = intent1.getStringExtra("helpcontent_catid");

        }

       Intent intent = getIntent();
        if (intent != null) {

            category_id = intent.getStringExtra("category_id");

        }
        getHelpSubCategory();
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }
    public void getHelpSubCategory() {
        dialog.show("");

        HelpSubCategoryAuthModel model = new HelpSubCategoryAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setCategory_id(category_id);

        WebServiceModel.getRestApi().getHelpSubCategory(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<HelpSubCategoryResponse>() {
                    @Override
                    public void onNext(HelpSubCategoryResponse helpSubCategoryResponse) {
                        String msg = helpSubCategoryResponse.getMsg();
                        if (msg.equals("success")) {

                            helpsubcategory = helpSubCategoryResponse.getHelpsubcategory();

                            if (helpsubcategory != null) {

                                help2Adapter = new Help2Adapter(helpsubcategory, context);
                                using_tsta_recy.setAdapter(help2Adapter);

                            }
                            txt_title.setText(helpSubCategoryResponse.getHelpcategory());


                        }
                        dialog.dismiss("");
                    }

                    @Override
                    public void onError(Throwable e) {

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
            Intent intent = new Intent(context, HelpActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        else  if (id == contact.getId()){
            Intent intent = new Intent(context, ContactUsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }
}