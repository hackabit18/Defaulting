package com.vanshika.hackabit.medai.Fragments;

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
import android.view.View;
import android.view.ViewGroup;

import com.vanshika.hackabit.medai.ActiveFragment;
import com.vanshika.hackabit.medai.Adapters.CurrentPresAdapter;
import com.vanshika.hackabit.medai.Models.CurrentMedicine;
import com.vanshika.hackabit.medai.R;
import com.vanshika.hackabit.medai.Room.AppDatabase;
import com.vanshika.hackabit.medai.Room.HistoryDose;
import com.vanshika.hackabit.medai.Room.NewDose;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment implements ActiveFragment{
    CurrentPresAdapter adapter;
    List<NewDose> list;
    RecyclerView recyclerView;
    LinearLayoutManager mLayoutManager;
    AppDatabase db;

    public HistoryFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        new task().execute("");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        new task().execute("");
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = new ArrayList<>();
        db = AppDatabase.getAppDatabase(getActivity().getApplicationContext());
        new task().execute("");
//        addAdapter();
/*
        mLayoutManager =
                new LinearLayoutManager(getActivity().getApplication(), LinearLayoutManager.VERTICAL, false);

        recyclerView=getActivity().findViewById(R.id.historyRecycler);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter=new CurrentPresAdapter(getActivity().getApplicationContext(),list,"old");
        recyclerView.setAdapter(adapter);
*/

    }

    private void addAdapter() {

        list.add(new NewDose("Pyrigesic", "Nausea, stomach pain, loss of appetite, upper stomach pain, dark urine",
                "The oral dose for adults is 325 to 650 mg every 4 to 6 hours.",
                "Hypersensitivity",
                "", "13:00", "20:00"));
        list.add(new NewDose("Dolo", "Nausea, stomach pain, loss of appetite.",
                "The oral dose for adults is 325 to 650 mg every 4 to 6 hours. ",
                "Hypersensitivity",
                "", "8:00", "17:00"));
        list.add(new NewDose("Sinarest", "Nausea, stomach pain, loss of appetite, upper stomach pain, dark urine, clay colored stool.",
                "The maximum daily dose is 4 grams.",
                "Hypersensitivity",
                "6:00", "13:00", "20:00"));
    }

    @Override
    public void fragmentBecameVisible() {
        new task().execute("");
    }

    public class task extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            list = AppDatabase.getAppDatabase(getActivity().getApplicationContext()).userDao().getHistoryDose();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mLayoutManager =
                    new LinearLayoutManager(getActivity().getApplication(), LinearLayoutManager.VERTICAL, false);

            recyclerView = getActivity().findViewById(R.id.historyRecycler);

            adapter = new CurrentPresAdapter(getActivity().getApplicationContext(), list, "old");
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(mLayoutManager);
        }
    }
}
