package com.vanshika.hackabit.medai.AlarmManager

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.preference.PreferenceManager
import android.support.annotation.RequiresApi
import android.support.v4.app.JobIntentService
import android.util.Log
import java.util.*





/*
*   service that gets called after alarmReceiver
* */
class Reminder : JobIntentService() {
    override fun onHandleWork(intent: Intent) {
        Log.e("OnHandleWork", "Success")
    }

    var str = String()


    var id: Int = 0
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(10,showNotification("test", "test", 0) )
        str = intent!!.getStringExtra("Time")
        if (str == "Morning") {
            id = 2
        } else
            id = 1
        if (PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("IS_CHECKED_IN", 0) != 0 && str.equals("Morning")) {
            stopForeground(true)
            return super.onStartCommand(intent, flags, startId)
        }
        if ((PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("IS_CHECKED_IN", 0) == 0 || PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt("IS_CHECKED_OUT", 0) != 0) && (str.equals("Evening") || str.equals("Midnight"))) {
            stopForeground(true)
            return super.onStartCommand(intent, flags, startId)
        }
        if(!valid()){
            stopForeground(true)
            return super.onStartCommand(intent, flags, startId)
        }
        if (str != "Midnight") {
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (str == "Morning")
                notificationManager.notify(id, showNotification("Reminder", "Please check in the application.", 1))
            else
                notificationManager.notify(id, showNotification("Reminder", "Please check out the application.", 1))

        } /*else {
            var sharedPreference = PreferenceManager.getDefaultSharedPreferences(applicationContext)
            sharedPreference.edit().putInt("IS_CHECKED_OUT", 1).apply()
            sharedPreference.edit().putString("LocationCheckIn", "Location not available").apply()
            sharedPreference.edit().putString("CheckOutTime", "11:59").apply()
            sharedPreference.edit().putBoolean("Leave", false).apply()
            sharedPreference.edit().putBoolean("LeaveSent", false).apply()
            sharedPreference.edit().putBoolean("HalfDay", false).apply()
            sharedPreference.edit().putBoolean("HalfDaySent", false).apply()
            if (sharedPreference.getInt("IS_CHECKED_IN", 0) != 0) {
                sharedPreference.edit().putInt("IS_CHECKED_OUT", 1).apply()
                var sta  = Static(applicationContext)
                sta.updateData("Check Out","Auto Check Out - Location not Available", "", "", "")

            }
        }*/
        stopForeground(true)
        return super.onStartCommand(intent, flags, startId)
    }



    fun valid() : Boolean {
        val date = Date()
        val week = Calendar.getInstance().get(Calendar.WEEK_OF_MONTH)
        if (((week == 1 || week ==3)&&date.day == 6)||date.day == 7 || PreferenceManager.getDefaultSharedPreferences(applicationContext).getBoolean("Leave", false)) {
            return false
        }
        return true
    }
    @SuppressLint("NewApi")
    fun showNotification(title: String, message: String, i: Int): Notification {
        var notification = Notification()
        try {
            /*val intent1 = Intent(this, NotificationButtonListener::class.java)
            val intent2 = Intent(this, NotificationButtonListener::class.java)
            val intent3 = Intent(this, NotificationButtonListener::class.java)
            val intent4 = Intent(this, NotificationButtonListener::class.java)
            intent1.action = "CheckIn"
            intent1.putExtra("Action", "CheckIn")
            intent2.action = "HalfDay"
            intent2.putExtra("Action", "HalfDay")
            intent3.action = "Leave"
            intent3.putExtra("Action", "Leave")
            intent4.action = "CheckOut"
            intent4.putExtra("Action", "CheckOut")
            val pendingIntent1 = PendingIntent.getBroadcast(applicationContext, 1, intent1, PendingIntent.FLAG_UPDATE_CURRENT)
            val pendingIntent2 = PendingIntent.getBroadcast(applicationContext, 1, intent2, PendingIntent.FLAG_UPDATE_CURRENT)
            val pendingIntent3 = PendingIntent.getBroadcast(applicationContext, 1, intent3, PendingIntent.FLAG_UPDATE_CURRENT)
            val pendingIntent4 = PendingIntent.getBroadcast(applicationContext, 1, intent4, PendingIntent.FLAG_UPDATE_CURRENT)
            val action1 = Notification.Action(R.drawable.ic_internet, "Check In", pendingIntent1)
            val action2 = Notification.Action(R.drawable.ic_internet, "Half Day", pendingIntent2)
            val action3 = Notification.Action(R.drawable.ic_internet, "Leave", pendingIntent3)
            val action4 = Notification.Action(R.drawable.ic_internet, "Check Out", pendingIntent4)
            notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && str.equals("Morning") && i == 1) {
                Notification.Builder(this, "1")
                        .setSmallIcon(R.drawable.so_buddy)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setAutoCancel(true)
                        .addAction(action1)
                        .addAction(action2)
                        .addAction(action3)
                        .build()
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && str.equals("Evening") && i == 1) {
                Notification.Builder(this, "1")
                        .setSmallIcon(R.drawable.so_buddy)
                        .setContentTitle(title)
                        .setContentText(message)
                        .addAction(action4)
                        .setAutoCancel(true)
                        .build()

            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && i == 0) {
                Notification.Builder(this, "1")
                        .setSmallIcon(R.drawable.so_buddy)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setAutoCancel(true)
                        .build()


            } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O && str.equals("Morning") && i == 1) {
                Notification.Builder(this)
                        .setSmallIcon(R.drawable.so_buddy)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setAutoCancel(true)
                        .addAction(action1)
                        .addAction(action2)
                        .addAction(action3)
                        .build()
            } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O && str.equals("Evening") && i == 1) {

                Notification.Builder(this)
                        .setSmallIcon(R.drawable.so_buddy)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setAutoCancel(true)
                        .addAction(action4)
                        .build()

            } else {
                Notification.Builder(this)
                        .setSmallIcon(R.drawable.so_buddy)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setAutoCancel(true)
                        .build()
            }*/
            notification.defaults = notification.defaults or Notification.DEFAULT_SOUND
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            return notification
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopForeground(true)
    }


    override fun onBind(arg0: Intent): IBinder? {
        return null
    }
}