package com.vanshika.hackabit.medai.AlarmClock;

import android.app.Activity;
import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.JobIntentService;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.ContextThemeWrapper;

import com.vanshika.hackabit.medai.R;

public class BackgroundService extends
        IntentService {
    public static final String ACTION = "ke.co.appslab.androidbackgroundservices.Receivers.ResponseBroadcastReceiver";

    public BackgroundService(String name) {
        super(name);
    }

    // Must create a default constructor
    public BackgroundService() {
        // Used to name the worker thread, important only for debugging.
        super("backgroundService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("Service", "Created");
        startForeground(1, notification());
        Vibrator v = (Vibrator) getSystemService(ContextThemeWrapper.VIBRATOR_SERVICE);
        v.vibrate(100);
        Log.v("Background", "Service");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
    }
/*

    @Override
    protected void onHandleWork(@NonNull Intent intent) {

    }
*/

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
        startForeground(1, notification());
        Vibrator v = (Vibrator) getSystemService(ContextThemeWrapper.VIBRATOR_SERVICE);
        v.vibrate(100);
        Log.v("Background", "Service");
    }

/*
    @Override
    protected void onHandleIntent(@Nullable Intent intent1) {
        // This describes what will happen when service is triggered
        Log.v("backgroundService","Service running");
*/
/*
        startForeground(1,notification());
        Vibrator v=(Vibrator)getSystemService(ContextThemeWrapper.VIBRATOR_SERVICE);
        v.vibrate(100);
        //create a broadcast to send the toast message
        Intent toastIntent= new Intent(ACTION);
        toastIntent.putExtra("resultCode", Activity.RESULT_OK);
        toastIntent.putExtra("toastMessage","I'M running after ever 15 minutes");
        sendBroadcast(toastIntent);
*//*


    }
*/

    Notification notification() {
        Intent intent = new Intent(this, AlarmActivity.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        return mBuilder.
                build();
    }
}