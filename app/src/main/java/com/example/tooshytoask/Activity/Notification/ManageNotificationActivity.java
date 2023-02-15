package com.example.tooshytoask.Activity.Notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Adapters.ManageNotificationAdapter;
import com.example.tooshytoask.AuthModels.ManageNotificationAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.ManageNotificationResponse;
import com.example.tooshytoask.Models.dataNotification;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ManageNotificationActivity extends AppCompatActivity implements View.OnClickListener{
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    RelativeLayout rel_back;
    RecyclerView recy_manage_notification;
    ManageNotificationAdapter adapter;
    ArrayList<dataNotification> dataNotification;

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

                            if (dataNotification.size() != 0){
                                adapter = new ManageNotificationAdapter(context, dataNotification);
                                recy_manage_notification.setAdapter(adapter);
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
}