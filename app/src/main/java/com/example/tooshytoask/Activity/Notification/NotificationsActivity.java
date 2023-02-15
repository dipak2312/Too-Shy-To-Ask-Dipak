package com.example.tooshytoask.Activity.Notification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
//import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Adapters.NotificationAdapter;
import com.example.tooshytoask.AuthModels.NotificationAuthModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.NotificationList;
import com.example.tooshytoask.Models.NotificationResponse;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.CustomProgressDialog;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class NotificationsActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    RelativeLayout rel_back;
    Toolbar toolbar;
    ImageView clear_all_notification, settings;
    RecyclerView rec_show_notification;
    ArrayList<NotificationList> notificationLists;
    NotificationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = NotificationsActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        settings = findViewById(R.id.settings);
        settings.setOnClickListener(this);
        toolbar = findViewById(R.id.toolbar);
        rec_show_notification = findViewById(R.id.rec_show_notification);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        setTitle("");
        /*clear_all_notification = findViewById(R.id.clear_all_notification);
        clear_all_notification.setOnClickListener(this);*/

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel =
                    new NotificationChannel("MyNotification", "PushNotification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notification_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.notification_clear:
                Toast.makeText(getApplicationContext(),"Clear All Notification",Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getNotification(){
        dialog.show("");

        NotificationAuthModel model = new NotificationAuthModel();
        model.setUser_id(spManager.getUserId());

        WebServiceModel.getRestApi().getNotification(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<NotificationResponse>() {
                    @Override
                    public void onNext(NotificationResponse notificationResponse) {
                        String msg = notificationResponse.getMsg();
                        if (msg.equals("success")){

                            notificationLists = notificationResponse.getNotificationList();

                            if (notificationLists.size() != 0){
                                adapter = new NotificationAdapter(context, notificationLists);
                                rec_show_notification.setAdapter(adapter);
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

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == rel_back.getId()){
            finish();
        }
        else if (id == settings.getId()){
            Intent intent = new Intent(context, ManageNotificationActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        /*else if (id == clear_all_notification.getId()){
            //onCreateOptionsMenu();
        }*/

    }
}