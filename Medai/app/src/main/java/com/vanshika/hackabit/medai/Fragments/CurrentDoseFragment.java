package com.vanshika.hackabit.medai.Fragments;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vanshika.hackabit.medai.Adapters.CurrentPresAdapter;
import com.vanshika.hackabit.medai.Models.CurrentMedicine;
import com.vanshika.hackabit.medai.Models.MedicineDb;
import com.vanshika.hackabit.medai.R;
import com.vanshika.hackabit.medai.Room.AppDatabase;
import com.vanshika.hackabit.medai.Room.NewDose;

import java.util.ArrayList;
import java.util.List;

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
        addAdapter();
        db = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, "new_dose")
                .build();
        new task().execute("");



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
