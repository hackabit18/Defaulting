package com.vanshika.hackabit.medai.AlarmManager

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.preference.PreferenceManager
import android.support.annotation.RequiresApi
import android.support.v4.app.JobIntentService
import android.util.Log
import com.vanshika.hackabit.medai.R
import java.util.*


/*
*   service that gets called after alarmReceiver
* */
import android.app.Service

import java.security.Provider
import java.time.DayOfWeek
import java.util.*

/*
*   receiver that gets called when notification is to be popped
* */

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        try {
            val intent1 = Intent(context, Reminder::class.java)
            val time = intent?.getStringExtra("Time")
            intent1.putExtra("Time", time)
            Log.e("AlarmReceiver", "Started")
            val date = Calendar.getInstance()
            if(date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                return
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context?.startForegroundService(intent1)
            } else {
                context?.startService(intent1)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}