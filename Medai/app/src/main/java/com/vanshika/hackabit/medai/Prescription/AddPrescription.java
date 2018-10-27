package com.vanshika.hackabit.medai.Prescription;

import android.app.TimePickerDialog;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.vanshika.hackabit.medai.R;
import com.vanshika.hackabit.medai.Room.AppDatabase;
import com.vanshika.hackabit.medai.Room.Prescription;

import java.util.Calendar;

public class AddPrescription extends AppCompatActivity {
FloatingActionButton fab;int c=2;
TextInputEditText name,medicine1,medicine2,medicine3,medicine4,medicine5,medicine6;
AppCompatButton time1,time2,time3;
String set1,set2,set3;
TextView t1,t2,t3;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prescription);
        name=findViewById(R.id.enterName);

        button=findViewById(R.id.button);
        //fab=findViewById(R.id.fab);

        time1=findViewById(R.id.time1);
        time2=findViewById(R.id.time2);
        time3=findViewById(R.id.time3);
        //t1=findViewById(R.id.t1);
        /*t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);*/
        time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddPrescription.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                      time1.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                set1=hour + ":" + minute;
                mTimePicker.setTitle("Select Time");

            }
        });
        time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddPrescription.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time2.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                set2=hour + ":" + minute;
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
        time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddPrescription.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time2.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                set3=hour + ":" + minute;
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AppDatabase
                        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"prescription")
                        .build();
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        
                    }
                });
                        db.userDao().insertAll(new Prescription(name.getText().toString(),set1,set2,set3));
                Toast.makeText(AddPrescription.this, "Dose added", Toast.LENGTH_SHORT).show();
                finish();


            }
        });
    }

}
/* AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        db.userDao().insertAll(new Prescription(name.getText().toString(),medicine1.getText().toString(),medicine2.getText().toString(),medicine3.getText().toString(),medicine4.getText().toString(),medicine5.getText().toString(),medicine6.getText().toString(),"","","","","",""));
                    }
                });*/