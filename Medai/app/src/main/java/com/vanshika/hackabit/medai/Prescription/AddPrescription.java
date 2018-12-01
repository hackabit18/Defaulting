package com.vanshika.hackabit.medai.Prescription;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.vanshika.hackabit.medai.AlarmClock.Alarm;
import com.vanshika.hackabit.medai.AlarmClock.AlarmActivity;
import com.vanshika.hackabit.medai.MainActivity;
import com.vanshika.hackabit.medai.R;
import com.vanshika.hackabit.medai.Room.AppDatabase;
import com.vanshika.hackabit.medai.Room.NewDose;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddPrescription extends AppCompatActivity {
    FloatingActionButton fab;
    int c = 2;
    TextInputEditText name, medicine1, medicine2, medicine3, medicine4, medicine5, medicine6;
    AppCompatButton time1, time2, time3;
    String set1, set2, set3;
    String nameString;
    String[] set=null;
    /*Intent intent1;
    PendingIntent p1;
    AlarmManager a;*/
    private int aTime1, aTime2, aTime3, hour1, minute1, hour2, minute2, hour3, minute3;
    TextView t1, t2, t3;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prescription);
        name = findViewById(R.id.enterName);
        name.setText(getIntent().getStringExtra("name"));

        button = findViewById(R.id.button);
        //fab=findViewById(R.id.fab);
        set=new String[3];
        time1 = findViewById(R.id.time1);
        time2 = findViewById(R.id.time2);
        time3 = findViewById(R.id.time3);
        String dose = getIntent().getStringExtra("name");
        final String dosage1 = getIntent().getStringExtra("dosage");
        final String side1 = getIntent().getStringExtra("side1");
        final String not1 = getIntent().getStringExtra("not1");
        //t1=findViewById(R.id.t1);
        /*t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);*/
        time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                hour1 = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                minute1 = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddPrescription.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        if (selectedMinute<10){
                            time1.setText(selectedHour + ":0" + selectedMinute);
                            set[0]=selectedHour+":0"+selectedMinute;
                        }
                        else {
                            time1.setText(selectedHour+":"+selectedMinute);
                            set[0]=selectedHour+":"+selectedMinute;
                        }
                    }
                }, hour1, minute1, true);//Yes 24 hour time
                //set1 = hour1 + ":" + minute1;
                set[0]=hour1 + ":" + minute1;
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
                //aTime1=hour*3600 + minute*60;
            }
        });
        time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar mcurrentTime = Calendar.getInstance();
                int hour2 = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute2 = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddPrescription.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        if (selectedMinute<10){
                            time2.setText(selectedHour + ":0" + selectedMinute);
                            set[1]=selectedHour+":0"+selectedMinute;
                        }
                        else {
                            time2.setText(selectedHour+":"+selectedMinute);
                            set[1]=selectedHour+":"+selectedMinute;
                        }

                    }
                }, hour2, minute2, true);//Yes 24 hour time
                //set[1] = hour2 + ":" + minute2;
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
                //aTime2=hour*3600+minute*60;
            }
        });
        time3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour3 = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute3 = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddPrescription.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        if (selectedMinute<10){
                            time3.setText(selectedHour + ":0" + selectedMinute);
                            set[2]=selectedHour+":0"+selectedMinute;
                        }
                        else {
                            time3.setText(selectedHour+":"+selectedMinute);
                            set[2]=selectedHour+":"+selectedMinute;
                        }
                    }
                }, hour3, minute3, true);//Yes 24 hour time
                set[2] = hour3 + ":" + minute3;
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
                //aTime3=hour*3600+minute*60;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AppDatabase
                        db = AppDatabase.getAppDatabase(getApplicationContext());
                nameString = name.getText().toString();

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        db.userDao().inserNewDose(new NewDose(nameString, dosage1, side1, not1, set[0], set[1], set[2]));
                        Log.v("line116", db.toString() + " dosage:" + db.userDao().getNewDose().get(db.userDao().getNewDose().size() - 1).getDosage() + "nottaken:" + db.userDao().getNewDose().get(db.userDao().getNewDose().size() - 1).getName());
                        Log.v("line116", String.valueOf(db.userDao().returnCount()));

                    }
                });


                //int time=Integer.parseInt(editText.getText().toString());

                //aTime1=hour1*3600 + minute1*
                for (int i=0;i<3;i++){
                    Calendar c = Calendar.getInstance();
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH);
                    int day=c.get(Calendar.DATE);
                    String d=day+"";
                    if (day<10){
                        d="0"+day;
                    }
                    String dateString=month+1+" "+d+" "+set[i]+" "+year;
                    setAlarm(dateString,i);
                }



                Toast.makeText(AddPrescription.this, "Dose added", Toast.LENGTH_SHORT).show();
                finish();

                Intent intent = new Intent(AddPrescription.this, MainActivity.class);
                startActivity(intent);
            }
        });
        /*AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.userDao().insertAll(new Prescription(name.getText().toString(),medicine1.getText().toString(),medicine2.getText().toString(),medicine3.getText().toString(),medicine4.getText().toString(),medicine5.getText().toString(),medicine6.getText().toString(),"","","","","",""));
            }
        });*/
    }
    public void setAlarm(String givenDateString, int reqCode){
        Intent intent1 = new Intent(AddPrescription.this, Alarm.class);
        PendingIntent p1 = PendingIntent.getBroadcast(getApplicationContext(), reqCode, intent1, 0);
        AlarmManager a = (AlarmManager) getSystemService(ALARM_SERVICE);
        Date date = null;
        try {
            //String givenDateString = "11 01 22:49 2018";
            SimpleDateFormat sdf = new SimpleDateFormat("MM dd HH:mm yyyy");
            date = sdf.parse(givenDateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!date.toString().isEmpty())
        a.set(AlarmManager.RTC_WAKEUP, /*System.currentTimeMillis() + aTime1 * 1000*/date.getTime(),p1);
    }

}
