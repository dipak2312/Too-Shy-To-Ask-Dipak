package com.example.tooshytoask.activity.Help;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Fragment.AskExpertFragment;
import com.example.tooshytoask.activity.Expert.ExpertActivity;
import com.example.tooshytoask.adapters.HelpCategoryAdapter;
import com.example.tooshytoask.AuthModels.HelpCategoryAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.Help.HelpCategoryResponse;
import com.example.tooshytoask.Models.HelpCategory;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener{
    RecyclerView help_category_recy, recyclerView;
    SPManager spManager;
    Context context;
    ArrayList<com.example.tooshytoask.Models.Help.data> data;
    LinearLayout ask_the_expert_lay;
    HelpCategoryAdapter adapter;
    RelativeLayout rel_back;
    CustomProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = HelpActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);
        ask_the_expert_lay = findViewById(R.id.ask_the_expert_lay);
        ask_the_expert_lay.setOnClickListener(this);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        help_category_recy = findViewById(R.id.help_category_recy);
        help_category_recy.setLayoutManager(new GridLayoutManager(context,3, GridLayoutManager.VERTICAL, false));

        getHelpCategory();

    }
    public void getHelpCategory() {
        dialog.show("");

        HelpCategoryAuthModel model = new HelpCategoryAuthModel();
        model.setUser_id(spManager.getUserId());

        WebServiceModel.getRestApi().getHelpCategory(model)
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
                        dialog.dismiss("");

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
        else if (id == ask_the_expert_lay.getId()){
            Intent intent = new Intent(context, ExpertActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}