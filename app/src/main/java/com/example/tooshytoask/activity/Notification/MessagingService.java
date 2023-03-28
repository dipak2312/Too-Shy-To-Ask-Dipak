package com.example.tooshytoask.activity.Notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.tooshytoask.R;
import com.example.tooshytoask.activity.Blogs.DetailBlogActivity;
import com.example.tooshytoask.activity.Blogs.DetailEventActivity;
import com.example.tooshytoask.activity.LMS.CoursesDetailActivity;
import com.example.tooshytoask.activity.LMS.LessonDetailActivity;
import com.example.tooshytoask.activity.FAQ.FAQActivity;
import com.example.tooshytoask.activity.Game.GameMainPageActivity;
import com.example.tooshytoask.activity.Home.HomeActivity;
import com.example.tooshytoask.activity.InformationStoreHouse.InformationStorehouseActivity;
import com.example.tooshytoask.activity.Quiz.QuizActivity;
import com.example.tooshytoask.activity.Setting.Setting.UpdateProfileActivity;
import com.example.tooshytoask.activity.VideoGallery.AllVideoActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessagingService extends FirebaseMessagingService {

    Intent intent;
    String condition ;
    String type_id;


    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.e( "onNewToken: ",s );

    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getNotification()!= null) {
           pushNotification(
                   remoteMessage.getNotification().getTitle(),
                   remoteMessage.getNotification().getBody()
           );
        }

    }

    public void pushNotification(String title, String Msg){

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification;

        final String CHANNEL_ID = "push_notification";

        Intent intent = new Intent(this, NotificationsActivity.class);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);


        PendingIntent pendingIntent = PendingIntent.getActivity(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        switch(condition) {
            case "blog":
                intent = new Intent(this, DetailBlogActivity.class);
                intent.putExtra("blog",type_id);
                break;
            case "storehouse":
                intent = new Intent(this, InformationStorehouseActivity.class);
                intent.putExtra("storehouse",type_id);
                break;
            case "game":
                intent = new Intent(this, GameMainPageActivity.class);
                intent.putExtra("game",type_id);
                break;
            case "quiz":
                intent = new Intent(this, QuizActivity.class);
                intent.putExtra("quiz",type_id);
                break;
            case "event":
                intent = new Intent(this, DetailEventActivity.class);
                intent.putExtra("event",type_id);
                break;
            case "courses":
                intent = new Intent(this, CoursesDetailActivity.class);
                intent.putExtra("courses",type_id);
                break;
            case "courses_lession":
                intent = new Intent(this, LessonDetailActivity.class);
                intent.putExtra("courses_lession",type_id);
                break;
            case "faq":
                intent = new Intent(this, FAQActivity.class);
                intent.putExtra("faq",type_id);
                break;
            case "story":
                intent = new Intent(this, HomeActivity.class);
                intent.putExtra("story",type_id);
                break;
            case "video":
                intent = new Intent(this, AllVideoActivity.class);
                intent.putExtra("video",type_id);
                break;
            case "update_profile":
                intent = new Intent(this, UpdateProfileActivity.class);
                intent.putExtra("update_profile",type_id);
                break;

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Custom Channel";
            String description = "Channel for Push notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                channel = new NotificationChannel(CHANNEL_ID, name, importance);
            }
            channel.setDescription(description);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);

                notification = new Notification.Builder(this)
                        .setSmallIcon(R.drawable.tsta_icon)
                        .setContentIntent(pendingIntent)
                        .setContentTitle(title)
                        .setSubText(Msg)
                        .setAutoCancel(true)
                        .setChannelId(CHANNEL_ID)
                        .build();
            }
            else {
                notification = new Notification.Builder(this)
                        .setSmallIcon(R.drawable.tsta_icon)
                        .setContentIntent(pendingIntent)
                        .setContentTitle(title)
                        .setSubText(Msg)
                        .setAutoCancel(true)
                        .build();
            }

            if (notificationManager != null){
                notificationManager.notify(1, notification);
            }

        }
    }
}