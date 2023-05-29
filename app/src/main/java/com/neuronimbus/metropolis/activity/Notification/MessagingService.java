package com.neuronimbus.metropolis.activity.Notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.activity.Blogs.DetailBlogActivity;
import com.neuronimbus.metropolis.activity.Blogs.DetailEventActivity;
import com.neuronimbus.metropolis.activity.LMS.CoursesDetailActivity;
import com.neuronimbus.metropolis.activity.LMS.LessonDetailActivity;
import com.neuronimbus.metropolis.activity.FAQ.FAQActivity;
import com.neuronimbus.metropolis.activity.Game.GameMainPageActivity;
import com.neuronimbus.metropolis.activity.Home.HomeActivity;
import com.neuronimbus.metropolis.activity.InformationStoreHouse.InformationStorehouseActivity;
import com.neuronimbus.metropolis.activity.Quiz.QuizActivity;
import com.neuronimbus.metropolis.activity.Setting.Setting.UpdateProfileActivity;
import com.neuronimbus.metropolis.activity.VideoGallery.AllVideoActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessagingService extends FirebaseMessagingService {

    Intent intent;
    String condition ;
    String type_id;
    Context context;


    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.e( "onNewToken: ",s );

    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (!condition.equals("")) {
             intent = new Intent(this, NotificationsActivity.class);
            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
        }

        if (!type_id.equals("")) {
             intent = new Intent(this, NotificationsActivity.class);
            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
        }

        if (remoteMessage.getNotification()!= null) {

            type_id = remoteMessage.getMessageId();
            condition = remoteMessage.getMessageType();

           pushNotification(
                   remoteMessage.getNotification().getTitle(),
                   remoteMessage.getNotification().getBody()
           );
        }

    }

    public void pushNotification(String title, String Msg){

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification;

        final String CHANNEL_ID = "TSTA";

        intent = new Intent(this, NotificationsActivity.class);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);


        PendingIntent pendingIntent = PendingIntent.getActivity(this, 100, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

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

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);

                notification = new Notification.Builder(this,CHANNEL_ID)
                        .setSmallIcon(R.drawable.tsta_icon)
                        .setContentIntent(pendingIntent)
                        .setContentTitle(title)
                        .setSubText(Msg)
                        .addAction(R.drawable.tsta_icon,title, pendingIntent)
                        .setAutoCancel(true)
                        .setChannelId(CHANNEL_ID)
                        .build();
            }
            else {
                notification = new Notification.Builder(this,CHANNEL_ID)
                        .setSmallIcon(R.drawable.tsta_icon)
                        .setContentIntent(pendingIntent)
                        .setContentTitle(title)
                        .setSubText(Msg)
                        .addAction(R.drawable.tsta_icon,title, pendingIntent)
                        .setAutoCancel(true)
                        .build();
            }

            if (notificationManager != null){
                notificationManager.notify(1, notification);
            }

        }
    }
}
