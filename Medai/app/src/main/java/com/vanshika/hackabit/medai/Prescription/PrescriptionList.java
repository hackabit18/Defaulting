package com.vanshika.hackabit.medai.Prescription;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.vanshika.hackabit.medai.Adapters.PrescriptionListAdapter;
import com.vanshika.hackabit.medai.R;
import com.vanshika.hackabit.medai.Room.AppDatabase;
import com.vanshika.hackabit.medai.Room.Prescription;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionList extends AppCompatActivity {
List<Prescription> list;
PrescriptionListAdapter adapter;
RecyclerView recyclerView;
LinearLayoutManager mLayoutManager;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_list);
        getItemsInList();
        mLayoutManager =
                new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false);
        recyclerView=findViewById(R.id.prescriptionRecycler);

        recyclerView.setLayoutManager(mLayoutManager);
        button=findViewById(R.id.addNewPrescription);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PrescriptionList.this,AddPrescription.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void getItemsInList() {
        list=new ArrayList<>();
        final AppDatabase
                db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"cars")
                .build();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                list=db.userDao().getAllPrescriptions();
                adapter=new PrescriptionListAdapter(getApplicationContext(),list);
                recyclerView.setAdapter(adapter);
            }
        });

        /*list.add("prescription1");
        list.add("prescription2");
        list.add("prescription3");
        list.add("prescription4");*/
    }
}
