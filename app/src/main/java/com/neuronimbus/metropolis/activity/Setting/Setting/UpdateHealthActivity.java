package com.neuronimbus.metropolis.activity.Setting.Setting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.Utils.LocaleHelper;
import com.neuronimbus.metropolis.adapters.HealthAdapter;
import com.neuronimbus.metropolis.AuthModels.HealthIssueModel;
import com.neuronimbus.metropolis.AuthModels.UpdateProfileAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.HealthIssueResponse;
import com.neuronimbus.metropolis.Models.HealthIssuseList;
import com.neuronimbus.metropolis.Models.UpdateProfile.UpdateProfileResponse;
import com.neuronimbus.metropolis.Models.UpdateProfile.health_issues;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.neuronimbus.metropolis.Utils.OnClickListner;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class UpdateHealthActivity extends AppCompatActivity implements View.OnClickListener, OnClickListner{
    Context context;
    SPManager spManager;
    RecyclerView health_recy, recyclerView;
    ArrayList<HealthIssuseList>healthIssuseList;
    ArrayList<health_issues> health_issues;
    HealthAdapter adapter;
    RelativeLayout rel_back, health;
    Button yes_btn, no_btn, update_btn2;
    OnClickListner onclicklistener;
    CustomProgressDialog dialog;
    String action = "healthissue", healthissueId="", health_status="";
    ArrayList<String>healthissueIds=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_health);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = UpdateHealthActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        yes_btn = findViewById(R.id.yes_btn);
        yes_btn.setOnClickListener(this);
        no_btn = findViewById(R.id.no_btn);
        no_btn.setOnClickListener(this);
        update_btn2 = findViewById(R.id.update_btn2);
        update_btn2.setOnClickListener(this);
        health = findViewById(R.id.health);
        health.setOnClickListener(this);

        health_recy = findViewById(R.id.health_recy);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        health_recy.setLayoutManager(linearLayoutManager1);
        healthIssues();
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
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
    public void healthIssues() {
        dialog.show("");

        HealthIssueModel model = new HealthIssueModel();
        model.setUser_id(spManager.getUserId());
        WebServiceModel.getRestApi().healthIssues(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<HealthIssueResponse>() {
                    @Override
                    public void onNext(HealthIssueResponse healthIssueResponse) {

                        String msg = healthIssueResponse.getMsg();

                        if (msg.equals("success")) {

                            healthIssuseList = healthIssueResponse.getHealthIssuseList();
                            health_status = healthIssueResponse.getIsHealthIssue();

                            if (health_status == null){
                                health.setVisibility(View.GONE);
                                yes_btn.setBackgroundResource(R.drawable.gender_border_inactive);
                                yes_btn.setTextColor(ContextCompat.getColor(context, R.color.black));
                                no_btn.setBackgroundResource(R.drawable.gender_border_inactive);
                                no_btn.setTextColor(ContextCompat.getColor(context, R.color.black));
                            }

                            else if (health_status.equals("Yes")){
                                health.setVisibility(View.VISIBLE);
                                yes_btn.setBackgroundResource(R.drawable.gender_border_active);
                                yes_btn.setTextColor(ContextCompat.getColor(context, R.color.white));
                            }
                            else if (health_status.equals("No")){
                                health.setVisibility(View.GONE);
                                no_btn.setBackgroundResource(R.drawable.gender_border_active);
                                no_btn.setTextColor(ContextCompat.getColor(context, R.color.white));
                            }

                            if (healthIssuseList != null) {
                                CallAdapter();
                            }

                            for(int i=0;i<healthIssuseList.size();i++)
                            {

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

    public void CallAdapter(){
        adapter = new HealthAdapter(healthIssuseList,this, context);
        health_recy.setAdapter(adapter);

    }

    public void getUserProfileUpdate(){
        dialog.show("");

        UpdateProfileAuthModel model = new UpdateProfileAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setHealthissue_id(healthissueIds);
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
         else if (id == update_btn2.getId()){
             getUserProfileUpdate();
             Intent intent = new Intent(context, UpdateProfileActivity.class);
             intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             startActivity(intent);
             finish();
         }
         else if (id == yes_btn.getId()){
             health.setVisibility(View.VISIBLE);
             for(int i=0;i<healthIssuseList.size();i++)
             {
                 healthIssuseList.get(i).isSelected=false;
                 adapter.notifyDataSetChanged();
             }
             yes_btn.setBackgroundResource(R.drawable.gender_border_active);
             yes_btn.setTextColor(ContextCompat.getColor(context, R.color.white));
             no_btn.setBackgroundResource(R.drawable.gender_border_inactive);
             no_btn.setTextColor(ContextCompat.getColor(context, R.color.black));

         } else if (id == no_btn.getId()){
             health.setVisibility(View.GONE);
             no_btn.setBackgroundResource(R.drawable.gender_border_active);
             no_btn.setTextColor(ContextCompat.getColor(context, R.color.white));
             yes_btn.setBackgroundResource(R.drawable.gender_border_inactive);
             yes_btn.setTextColor(ContextCompat.getColor(context, R.color.black));

         }
    }

    @Override
    public void onClickData(int position, String id) {
        healthissueId = id;
        if(healthIssuseList.get(position).isSelected)
        {
            healthissueIds.add(healthissueId);
        }else
        {
            healthissueIds.add(healthissueId);
        }
    }
}