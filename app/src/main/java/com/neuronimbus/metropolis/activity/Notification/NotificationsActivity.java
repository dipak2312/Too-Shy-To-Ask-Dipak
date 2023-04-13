package com.neuronimbus.metropolis.activity.Notification;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
//import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.activity.Blogs.DetailBlogActivity;
import com.neuronimbus.metropolis.activity.FAQ.FAQActivity;
import com.neuronimbus.metropolis.activity.Game.GameHomeActivity;
import com.neuronimbus.metropolis.activity.Game.GameMainPageActivity;
import com.neuronimbus.metropolis.activity.InformationStoreHouse.InformationStoreHouseDetailActivity;
import com.neuronimbus.metropolis.activity.Quiz.QuizActivity;
import com.neuronimbus.metropolis.activity.Setting.Setting.UpdateProfileActivity;
import com.neuronimbus.metropolis.activity.VideoGallery.VideoGallerySingleActivity;
import com.neuronimbus.metropolis.activity.story.StoryActivity;
import com.neuronimbus.metropolis.adapters.NotificationAdapter;
import com.neuronimbus.metropolis.AuthModels.ClearNotificationAuthModel;
import com.neuronimbus.metropolis.AuthModels.NotificationAuthModel;
import com.neuronimbus.metropolis.AuthModels.SingleClearNotificationAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.ClearNotificationResponse;
import com.neuronimbus.metropolis.Models.NotificationList;
import com.neuronimbus.metropolis.Models.NotificationResponse;
import com.neuronimbus.metropolis.Models.SingleClearNotificationResponse;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class NotificationsActivity extends AppCompatActivity implements View.OnClickListener, NotificationAdapter.ClearNotification {
    Context context;
    SPManager spManager;
    CustomProgressDialog dialog;
    RelativeLayout rel_back, notification_recy_lay;
    Toolbar toolbar;
    ImageView clear_all_notification, settings;
    RecyclerView rec_show_notification;
    NotificationAdapter adapter;
    NotificationAdapter.ClearNotification ClearNotification;
    String action = "clear", notification_id = "", notification_read_status = "";
    ArrayList<NotificationList> notificationLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        context = NotificationsActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);

        notification_recy_lay = findViewById(R.id.notification_recy_lay);
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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rec_show_notification.setLayoutManager(linearLayoutManager);

        getNotification();
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
                AllClearNotificationPopup();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void ClearNotification(String id){
        dialog.show("");

        ClearNotificationAuthModel model = new ClearNotificationAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setAction(action);

        WebServiceModel.getRestApi().ClearNotification(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ClearNotificationResponse>() {
                    @Override
                    public void onNext(ClearNotificationResponse clearNotificationResponse) {
                        String msg = clearNotificationResponse.getMsg();
                            dialog.dismiss("");
                        if (msg.equals("Notification Cleared")){

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

    public void SingleClearNotification(){
        dialog.show("");

        SingleClearNotificationAuthModel model = new SingleClearNotificationAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setNotification_id(notification_id);

        WebServiceModel.getRestApi().SingleClearNotification(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<SingleClearNotificationResponse>() {
                    @Override
                    public void onNext(SingleClearNotificationResponse singleClearNotificationResponse) {
                        String msg = singleClearNotificationResponse.getMsg();
                        dialog.dismiss("");
                        if (msg.equals("Notification Deleted")){

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
                        dialog.dismiss("");
                        if (msg.equals("success")){

                            notificationLists = notificationResponse.getNotificationList();

                            if (notificationLists.size() != 0){
                                notification_recy_lay.setVisibility(View.VISIBLE);
                                adapter = new NotificationAdapter(context, notificationLists, NotificationsActivity.this);
                                rec_show_notification.setAdapter(adapter);
                            }

                            if (notificationLists.size() == 0){
                                notification_recy_lay.setVisibility(View.GONE);
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

    }

    public void AllClearNotificationPopup(){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.all_clear_notification);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.logout_popup));

        Button yes_btn = dialog.findViewById(R.id.yes_btn);
        Button no_btn = dialog.findViewById(R.id.no_btn);

        yes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClearNotification(notification_id);
                Toast.makeText(getApplicationContext(),"Clear All Notification",Toast.LENGTH_LONG).show();
                dialog.dismiss();

            }
        });

        no_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void SingleClearNotificationPopup(){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.single_notification_clear);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.logout_popup));

        Button yes_btn = dialog.findViewById(R.id.yes_btn);
        Button no_btn = dialog.findViewById(R.id.no_btn);

        yes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SingleClearNotification();
                getNotification();
                Toast.makeText(getApplicationContext(),"Clear Notification",Toast.LENGTH_LONG).show();
                dialog.dismiss();

            }
        });

        no_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void ClearNotificationClick(int position, String id, String status) {
        notification_id = id;
        notification_read_status =status;
        SingleClearNotificationPopup();

    }

    public void BlogRedirection(int position){


    }
}