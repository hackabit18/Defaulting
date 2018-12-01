package com.vanshika.hackabit.medai.AlarmClock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ToastBroadcastReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent= new Intent(context,BackgroundService.class);
        context.startService(serviceIntent);

    }
}