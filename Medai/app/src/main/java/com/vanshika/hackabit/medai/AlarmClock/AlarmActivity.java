package com.vanshika.hackabit.medai.AlarmClock;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.vanshika.hackabit.medai.AlarmClock.AlarmReceiver;
import com.vanshika.hackabit.medai.R;

import java.sql.Time;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class AlarmActivity extends Activity {
    Alarm broadcastReceiver;
    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker alarmTimePicker;
    private static AlarmActivity inst;
    private TextView alarmTextView;
    TimePicker myTimePicker;
    /*Button buttonstartSetDialog;
    TextView textAlarmPrompt;
    Button alarmToggle;
*/ private TextClock editTime;
    private EditText editText;
    private TimePicker button;
    private Button done;

    public static AlarmActivity instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        /*alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        alarmTextView = (TextView) findViewById(R.id.alarmText);
        alarmToggle = (Button) findViewById(R.id.alarmToggle);*/
        broadcastReceiver= new Alarm();
        IntentFilter intentFilter= new IntentFilter();
        intentFilter.addAction(BackgroundService.ACTION);
        registerReceiver(broadcastReceiver,intentFilter);
        editTime=(TextClock) findViewById(R.id.editTime);
        button=(TimePicker) findViewById(R.id.button);
        done=findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int time=Integer.parseInt(editText.getText().toString());
                Intent intent=new Intent(AlarmActivity.this,Alarm.class);
                PendingIntent p1=PendingIntent.getBroadcast(getApplicationContext(),0,intent,0);
                AlarmManager a=(AlarmManager) getSystemService(ALARM_SERVICE);
                a.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+time*1000,p1);
            }
        });*/
        Timer t=new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (editTime.getText().toString().equals(AlarmTime())){
                    Intent intent=new Intent(AlarmActivity.this,Alarm.class);
                    PendingIntent p1=PendingIntent.getBroadcast(getApplicationContext(),0,intent,0);
                    AlarmManager a=(AlarmManager) getSystemService(ALARM_SERVICE);
                    //a.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+(button.getCurrentHour()*1000*60)+(button.),p1);
                }
            }
        },0,1000);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    public String AlarmTime(){
        Integer alarmHours=button.getCurrentHour();
        Integer alarmMInutes=button.getCurrentHour();
        String stringAlarmTime;

        if (alarmHours>12){
            alarmHours=alarmHours-12;
            stringAlarmTime=alarmHours.toString().concat(":").concat(alarmMInutes.toString()).concat(" PM");
        }
        else {
            stringAlarmTime=alarmHours.toString().concat(":").concat(alarmMInutes.toString()).concat(" AM");
        }
        return stringAlarmTime;
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter= new IntentFilter();
        intentFilter.addAction(BackgroundService.ACTION);
        registerReceiver(broadcastReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

   }
