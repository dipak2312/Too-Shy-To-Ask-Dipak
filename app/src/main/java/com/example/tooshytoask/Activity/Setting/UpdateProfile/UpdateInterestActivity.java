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
import android.widget.Toast;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Activity.Setting.UpdateProfileActivity;
import com.example.tooshytoask.Adapters.CategoryAdapter;
import com.example.tooshytoask.Adapters.UpdateCategoryAdapter;
import com.example.tooshytoask.AuthModels.HealthCateModel;
import com.example.tooshytoask.AuthModels.UpdateProfileAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.CategoryItem;
import com.example.tooshytoask.Models.HealthCateResponse;
import com.example.tooshytoask.Models.InformationStorehouseList;
import com.example.tooshytoask.Models.UpdateProfile.UpdateProfileResponse;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.ClickListener;
import com.example.tooshytoask.Utils.CustomProgressDialog;
import com.example.tooshytoask.Utils.OnClickListner;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class UpdateInterestActivity extends AppCompatActivity implements View.OnClickListener, OnClickListner{
    Context context;
    SPManager spManager;
    RecyclerView category_recy;
    CategoryAdapter adapter;
    ArrayList<InformationStorehouseList>informationStorehouseList;
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

        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        update_btn = findViewById(R.id.update_btn);
        update_btn.setOnClickListener(this);
        category_recy = findViewById(R.id.category_recy);

        category_recy.setLayoutManager(new GridLayoutManager(context,3, GridLayoutManager.VERTICAL, false));

        /*categoryItem = new ArrayList<>();

        categoryItem.add(new CategoryItem(R.drawable.reproduction,"Relationships",false));
        categoryItem.add(new CategoryItem(R.drawable.mental_health,"Sex & Sexuality",false));
        categoryItem.add(new CategoryItem(R.drawable.reproduction,"Reproduction",false));
        categoryItem.add(new CategoryItem(R.drawable.mental_health,"Mental Health",false));
        categoryItem.add(new CategoryItem(R.drawable.reproduction,"Education",false));
        categoryItem.add(new CategoryItem(R.drawable.mental_health,"Sexual Assault",false));*/

       // category_recy.setAdapter(new UpdateCategoryAdapter(informationStorehouseList,onclicklistener, clickListener));
        healthcategory();
    }

    public void healthcategory() {
        dialog.show("");

        HealthCateModel model = new HealthCateModel();
        model.setUser_id(spManager.getUserId());
        WebServiceModel.getRestApi().healthcategory(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<HealthCateResponse>() {
                    @Override
                    public void onNext(HealthCateResponse healthCateResponse) {

                        String msg = healthCateResponse.getMsg();

                        if (msg.equals("success")) {

                            informationStorehouseList = healthCateResponse.getInformationStorehouseList();
                            for(int i=0;i<informationStorehouseList.size();i++)
                            {
                                informationStorehouseList.get(i).isSelected=false;
                            }


                            if (informationStorehouseList != null) {
                                CallAdapter();
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

    public void CallAdapter()

    {
        adapter = new CategoryAdapter(informationStorehouseList, context,this);
        category_recy.setAdapter(adapter);
    }

    public void getUserProfile(){
        dialog.show("");
        dialog.dismiss("");

        UpdateProfileAuthModel model = new UpdateProfileAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setHealth_id(model.getHealth_id());

        WebServiceModel.getRestApi().getUserProfile(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<UpdateProfileResponse>() {
                    @Override
                    public void onNext(UpdateProfileResponse updateProfileResponse) {
                        String msg = updateProfileResponse.getMsg();

                        if (msg.equals("Profile Updated")){

                            //spManager.setFirstName(edit_name.getText().toString().trim());

                        }
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

            Intent intent = new Intent(context, UpdateProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        if (id == update_btn.getId()){

            getUserProfile();
            Intent intent = new Intent(context, UpdateProfileActivity.class);
            Toast.makeText(context, "Update Your Interests", Toast.LENGTH_SHORT).show();
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            dialog.show("");
            dialog.dismiss("");
        }

    }

    @Override
    public void onClickData(int position, String id) {

    }
}
