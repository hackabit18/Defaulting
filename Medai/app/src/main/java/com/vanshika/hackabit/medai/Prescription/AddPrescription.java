package com.vanshika.hackabit.medai.Prescription;

import android.arch.persistence.room.Room;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vanshika.hackabit.medai.R;
import com.vanshika.hackabit.medai.Room.AppDatabase;

public class AddPrescription extends AppCompatActivity {
FloatingActionButton fab;int c=2;
TextInputEditText name,medicine1,medicine2,medicine3,medicine4,medicine5,medicine6;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prescription);
        medicine1=findViewById(R.id.enterMedicine1);
        medicine2=findViewById(R.id.enterMedicine2);
        medicine3=findViewById(R.id.enterMedicine3);
        medicine4=findViewById(R.id.enterMedicine4);
        medicine5=findViewById(R.id.enterMedicine5);
        medicine6=findViewById(R.id.enterMedicine6);
        button=findViewById(R.id.button);
        fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(c){
                    case 2:medicine2.setVisibility(View.VISIBLE);
                    break;
                    case 3:medicine3.setVisibility(View.VISIBLE);
                        break;
                    case 4:medicine4.setVisibility(View.VISIBLE);
                        break;
                    case 5:medicine5.setVisibility(View.VISIBLE);
                        break;
                    case 6:medicine6.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AppDatabase
                        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"cars")
                        .build();

            }
        });
    }
}
