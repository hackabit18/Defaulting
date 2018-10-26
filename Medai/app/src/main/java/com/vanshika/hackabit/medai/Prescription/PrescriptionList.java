package com.vanshika.hackabit.medai.Prescription;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.vanshika.hackabit.medai.Adapters.PrescriptionListAdapter;
import com.vanshika.hackabit.medai.R;

import java.util.ArrayList;

public class PrescriptionList extends AppCompatActivity {
ArrayList<String> list;
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
        adapter=new PrescriptionListAdapter(getApplicationContext(),list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(mLayoutManager);
        button=findViewById(R.id.addNewPrescription);

    }

    private void getItemsInList() {
        list=new ArrayList<>();
        list.add("prescription1");
        list.add("prescription2");
        list.add("prescription3");
        list.add("prescription4");
    }
}
