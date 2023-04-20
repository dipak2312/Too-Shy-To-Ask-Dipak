package com.neuronimbus.metropolis.activity.Notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.Utils.OnClickListner;
import com.neuronimbus.metropolis.adapters.ManageNotificationAdapter;
import com.neuronimbus.metropolis.AuthModels.ManageNotificationAuthModel;
import com.neuronimbus.metropolis.AuthModels.ManageNotificationListUpdateAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Interface.onManageNotification;
import com.neuronimbus.metropolis.Models.ManageNotificationListUpdateResponse;
import com.neuronimbus.metropolis.Models.ManageNotificationResponse;
import com.neuronimbus.metropolis.Models.dataNotification;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ManageNotificationActivity extends AppCompatActivity implements View.OnClickListener, OnClickListner {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    RelativeLayout rel_back;
    RecyclerView recy_manage_notification;
    ManageNotificationAdapter adapter;
    ArrayList<dataNotification> dataNotification;
    onManageNotification onManageNotification;
    String module_id = "";
    ArrayList<String>module_ids=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_notification);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = ManageNotificationActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);

        recy_manage_notification = findViewById(R.id.recy_manage_notification);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recy_manage_notification.setLayoutManager(linearLayoutManager);

        getManageNotification();
    }

    public void getManageNotification(){
        dialog.show("");

        ManageNotificationAuthModel model = new ManageNotificationAuthModel();
        model.setUser_id(spManager.getUserId());

        WebServiceModel.getRestApi().getManageNotification(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ManageNotificationResponse>() {
                    @Override
                    public void onNext(ManageNotificationResponse manageNotificationResponse) {
                        String msg = manageNotificationResponse.getMsg();
                            dialog.dismiss("");
                        if (msg.equals("success")){

                            dataNotification = manageNotificationResponse.getDataNotification();

                            if (dataNotification != null){
                                CallAdapter();
                            }

                            for(int i=0;i<dataNotification.size();i++)
                            {

                            }

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

    public void CallAdapter(){
        adapter = new ManageNotificationAdapter(context, dataNotification, this);
        recy_manage_notification.setAdapter(adapter);
    }

    public void main(String[] args){

    }

    public void getManageNotificationUpdate(String id){

        ManageNotificationListUpdateAuthModel model = new ManageNotificationListUpdateAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setModule_ids(module_ids);

        WebServiceModel.getRestApi().getManageNotificationUpdate(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ManageNotificationListUpdateResponse>() {
                    @Override
                    public void onNext(ManageNotificationListUpdateResponse manageNotificationListUpdateResponse) {

                        String msg = manageNotificationListUpdateResponse.getMsg();

                        if (msg.equals("success")){
                            getManageNotification();
                        }
                        else {

                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

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
            Intent intent = new Intent(context, NotificationsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }


    }

    @Override
    public void onClickData(int position, String id) {
          module_ids.clear();
          module_id = id;
          module_ids.add(module_id);


        Log.d("saggi",module_ids.toString());
        getManageNotificationUpdate(id);



    }
}