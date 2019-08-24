package com.example.android.firebasenotification;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyMessagingService extends FirebaseMessagingService {
    String value;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        value = remoteMessage.getData().get("TargetActivity");
        showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
    }

    public void showNotification(String title,String message)
    {
        //Intent resultIntent=new Intent(this,MainActivity.class);
        try {
            Class<?> c = Class.forName(value);
            Intent resultIntent = new Intent(this, c);
            PendingIntent resultPendingIntent=PendingIntent.getActivity(this,1,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "MyNotifications")
                    .setContentTitle(title)
                    .setSmallIcon(R.drawable.mychat)
                    .setContentIntent(resultPendingIntent)
                    .setAutoCancel(true)
                    .setContentText(message);
            NotificationManagerCompat manager=NotificationManagerCompat.from(this);
            manager.notify(999,builder.build());

        } catch (ClassNotFoundException ignored) {

        }


    }
}
