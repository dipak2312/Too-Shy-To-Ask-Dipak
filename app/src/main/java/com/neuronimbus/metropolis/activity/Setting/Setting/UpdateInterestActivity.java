package com.neuronimbus.metropolis.activity.Setting.Setting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.adapters.CategoryAdapter;
import com.neuronimbus.metropolis.AuthModels.HealthCateModel;
import com.neuronimbus.metropolis.AuthModels.UpdateProfileAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.HealthCateResponse;
import com.neuronimbus.metropolis.Models.InformationStorehouseList;
import com.neuronimbus.metropolis.Models.UpdateProfile.UpdateProfileResponse;
import com.neuronimbus.metropolis.Models.UpdateProfile.health_interest;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.ClickListener;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.neuronimbus.metropolis.Utils.OnClickListner;

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
    ArrayList<health_interest>health_interest;
    RelativeLayout rel_back;
    CustomProgressDialog dialog;
    Button update_btn;
    ClickListener clickListener;
    OnClickListner onclicklistener;
    String action = "healthcategory", healthId="";
    ArrayList<String>helthIds=new ArrayList<>();

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


        healthcategory();

    }
    @Override
    protected void onStop() {
        super.onStop();
        dialog.dismiss("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialog.dismiss("");
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
                        dialog.dismiss("");
                        if (msg.equals("success")) {

                            informationStorehouseList = healthCateResponse.getInformationStorehouseList();

                            if (informationStorehouseList != null) {
                                CallAdapter();
                            }


                            for(int i=0;i<informationStorehouseList.size();i++)
                            {

                                }


                        }
                        else {

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

    public void CallAdapter()

    {
        adapter = new CategoryAdapter(informationStorehouseList, context,this);
        category_recy.setAdapter(adapter);
    }

    public void getUserProfileUpdate(){
        dialog.show("");

        UpdateProfileAuthModel model = new UpdateProfileAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setHealth_id(helthIds);
        model.setAction(action);

        WebServiceModel.getRestApi().getUserProfile(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<UpdateProfileResponse>() {
                    @Override
                    public void onNext(UpdateProfileResponse updateProfileResponse) {
                        String msg = updateProfileResponse.getMsg();
                        dialog.dismiss("");
                        if (msg.equals("Profile Updated")){

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dialog.dismiss("");
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
            getUserProfileUpdate();
            Intent intent = new Intent(context, UpdateProfileActivity.class);
            Toast.makeText(context, "Update Your Interests", Toast.LENGTH_SHORT).show();
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onClickData(int position, String id) {

        //helthIds.clear();
        healthId = id;
        //helthIds.add(healthId);

        //healthId = id;
        if(informationStorehouseList.get(position).isSelected)
        {
            helthIds.add(healthId);
        }
        else
        {
            helthIds.add(healthId);
        }

    }
}
