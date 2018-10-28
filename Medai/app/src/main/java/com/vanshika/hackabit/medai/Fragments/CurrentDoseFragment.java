package com.vanshika.hackabit.medai.Fragments;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vanshika.hackabit.medai.Adapters.CurrentPresAdapter;
import com.vanshika.hackabit.medai.MainActivity;
import com.vanshika.hackabit.medai.Models.CurrentMedicine;
import com.vanshika.hackabit.medai.Models.MedicineDb;
import com.vanshika.hackabit.medai.R;
import com.vanshika.hackabit.medai.Room.AppDatabase;
import com.vanshika.hackabit.medai.Room.NewDose;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CurrentDoseFragment extends Fragment {
    CurrentPresAdapter adapter;
    List<NewDose> list;
    RecyclerView recyclerView;
    LinearLayoutManager mLayoutManager;
    AppDatabase db;
    public CurrentDoseFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_current_dose, container, false);
    }

    /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }*/

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list=new ArrayList<>();
        //addAdapter();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            db = Room.inMemoryDatabaseBuilder(getActivity().getApplicationContext(), AppDatabase.class)
                    .build();
            //new task().execute("");
        }
        /*SharedPreferences prefs = getActivity().getSharedPreferences("newdose", Context.MODE_PRIVATE);
        int i=prefs.getInt("number",0);
        if(i!=0){
            String c="|";
            String card1=prefs.getString("card1",null);
            String name=card1.split(c)[0];
            String side=card1.split(c)[1];
            String dosage=card1.split(c)[2];
            String notTaken=card1.split(c)[3];
            String t1=card1.split(c)[4];
            String t2=card1.split(c)[5];
            String t3=card1.split(c)[6];
            list.add(new NewDose(name,side,dosage,notTaken,t1,t2,t3));
            Log.v("line84",name+" "+side);
        }*/

        /*if(i==2){
            String c="|";
            String card1=prefs.getString("card1",null);
            String name=card1.split(c)[0];
            String side=card1.split(c)[1];
            String dosage=card1.split(c)[2];
            String notTaken=card1.split(c)[3];
            String t1=card1.split(c)[4];
            String t2=card1.split(c)[5];
            String t3=card1.split(c)[6];
            list.add(new NewDose(name,side,dosage,notTaken,t1,t2,t3));
        }*/

        //new task().execute("");
        list.add(new NewDose("Pyrigesic","Nausea, stomach pain, loss of appetite, upper stomach pain, dark urine",
                "The oral dose for adults is 325 to 650 mg every 4 to 6 hours." ,
                "Hypersensitivity" ,
                "","13:00","20:00"));
        list.add(new NewDose("Dolo","Nausea, stomach pain, loss of appetite.",
                "The oral dose for adults is 325 to 650 mg every 4 to 6 hours. " ,
                "Hypersensitivity" ,
                "","8:00","17:00"));
        list.add(new NewDose("Sinarest","Nausea, stomach pain, loss of appetite, upper stomach pain, dark urine, clay colored stool.",
                "The maximum daily dose is 4 grams." ,
                "Hypersensitivity" ,
                "6:00","13:00","20:00"));

        mLayoutManager =
                new LinearLayoutManager(getActivity().getApplication(), LinearLayoutManager.VERTICAL, false);

        recyclerView=getActivity().findViewById(R.id.currentDoseRecycler);

        adapter=new CurrentPresAdapter(getActivity().getApplicationContext(),list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(mLayoutManager);


    }

    private void addAdapter() {



        //list.add(new NewDose("bvkse","ckjbakc","ckjabjc","cakjbc","cka","ckav","cka"));

    }
    public class task extends AsyncTask<String, String,String>{

        @Override
        protected String doInBackground(String... strings) {
            list=db.userDao().getNewDose();

            list.add(new NewDose("med1","side effects",
                    "dosage" ,
                    "note taken" ,
                    "t1","t2","t3"));
            list.add(new NewDose("med1","side effects",
                    "dosage" ,
                    "note taken" ,
                    "t1","t2","t3"));
            list.add(new NewDose("med1","side effects",
                    "dosage" ,
                    "note taken" ,
                    "t1","t2","t3"));

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mLayoutManager =
                    new LinearLayoutManager(getActivity().getApplication(), LinearLayoutManager.VERTICAL, false);

            recyclerView=getActivity().findViewById(R.id.currentDoseRecycler);

            adapter=new CurrentPresAdapter(getActivity().getApplicationContext(),list);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(mLayoutManager);
        }
    }

}
