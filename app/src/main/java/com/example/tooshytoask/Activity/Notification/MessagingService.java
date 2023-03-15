package com.example.tooshytoask.Activity.Notification;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.tooshytoask.Activity.Home.HomeActivity;
import com.example.tooshytoask.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MessagingService extends FirebaseMessagingService {

    Intent intent;


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

        PendingIntent pi = PendingIntent.getActivity(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

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
                        .setContentIntent(pi)
                        .setContentTitle(title)
                        .setSubText(Msg)
                        .setAutoCancel(true)
                        .setChannelId(CHANNEL_ID)
                        .build();
            }
            else {
                notification = new Notification.Builder(this)
                        .setSmallIcon(R.drawable.tsta_icon)
                        .setContentIntent(pi)
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

    /*@Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getData().size() > 0) {
            img_url = remoteMessage.getData().get("icon");

            if (!img_url.equals("")) {
                getBitmapfromUrl(img_url);
            }

            createNotification(remoteMessage, bitmap);
        }

    }

    public Bitmap getBitmapfromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        }
    }

    private void createNotification(RemoteMessage remoteMessage, Bitmap bitmap) {


        if (bitmap != null) {
            int notifyID = 1;
            String CHANNEL_ID = "my_channel_01";// The id of the channel.
            String name = "TooShyToAsk";// The user-visible name of the channel.
            int importance = NotificationManager.IMPORTANCE_HIGH;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            }

            intent = new Intent(this, NotificationsActivity.class);
            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("imageString", img_url);

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            PendingIntent resultIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
            NotificationCompat.Builder mNotificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.notification)
                    .setContentTitle(remoteMessage.getData().get("title"))
                    .setContentText(remoteMessage.getData().get("body"))
                    //.setContentTitle(remoteMessage.getNotification().getTitle())
                    //.setContentText(remoteMessage.getNotification().getBody())
                    .setChannelId(CHANNEL_ID)
                    .setStyle(new NotificationCompat.BigPictureStyle()
                            .bigPicture(bitmap))
                    .setColor(getResources().getColor(R.color.purple))
                    .setSound(defaultSoundUri)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(remoteMessage.getData().get("body")))
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setAutoCancel(true)
                    .setContentIntent(resultIntent);


            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(mChannel);
            }

            notificationManager.notify(0, mNotificationBuilder.build());


        } else {
            int notifyID = 1;
            String CHANNEL_ID = "my_channel_01";// The id of the channel.
            String name = "TooShyToAsk";// The user-visible name of the channel.
            int importance = NotificationManager.IMPORTANCE_HIGH;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            }

            intent = new Intent(this, NotificationsActivity.class);
            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
            //intent.putExtra("imageString", img_url);

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            PendingIntent resultIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
            NotificationCompat.Builder mNotificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.notification)
                    .setContentTitle(remoteMessage.getData().get("title"))
                    .setContentText(remoteMessage.getData().get("body"))
                    //.setContentTitle(remoteMessage.getNotification().getTitle())
                    //.setContentText(remoteMessage.getNotification().getBody())
                    .setChannelId(CHANNEL_ID)
                    .setSound(defaultSoundUri)
                    .setColor(getResources().getColor(R.color.purple))
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setAutoCancel(true)
                    .setContentIntent(resultIntent);


            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(mChannel);
            }

            notificationManager.notify(0, mNotificationBuilder.build());

        }
    }
}*/
