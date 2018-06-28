package dgsw.hs.kr.flow.Service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import dgsw.hs.kr.flow.Activity.MainActivity;
import dgsw.hs.kr.flow.Activity.NoticeListActivity;
import dgsw.hs.kr.flow.Activity.OutListActivity;
import dgsw.hs.kr.flow.Database.DBManager;
import dgsw.hs.kr.flow.R;

/**
 * Created by 조현재 on 2018-06-22.
 */

public class FCMService extends FirebaseMessagingService{
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        setDBUpdate(Integer.parseInt(remoteMessage.getData().get("idx")));
        sendNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody(), remoteMessage.getData().get("type"));
    }

    private void sendNotification(String title, String body, String type) {
        Intent intent = null;

        switch (type) {
            case "go_out":
            case "sleep_out":
                intent = new Intent(getApplicationContext(), OutListActivity.class);
                break;
            case "notice":
                intent = new Intent(getApplicationContext(), NoticeListActivity.class);
                break;
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = "channelId";
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "Default channel", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    private void setDBUpdate(int idx){
        Log.i("TEST getData().get(idx)","VALUES : "+  idx);
        Log.i("TEST getData().get(idx)","VALUES : "+  idx);

        DBManager dbManager = new DBManager(getApplicationContext(), "FlowUser.db", null, 1);
        dbManager.update_accept(idx);
    }
}
