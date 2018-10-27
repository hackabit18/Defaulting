package com.vanshika.hackabit.medai.Fragments;

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

import com.vanshika.hackabit.medai.Adapters.CurrentPresAdapter;
import com.vanshika.hackabit.medai.Models.CurrentMedicine;
import com.vanshika.hackabit.medai.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment{
    CurrentPresAdapter adapter;
    List<CurrentMedicine> list;
    RecyclerView recyclerView;
    LinearLayoutManager mLayoutManager;
    public HistoryFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addAdapter();
        mLayoutManager =
                new LinearLayoutManager(getActivity().getApplication(), LinearLayoutManager.VERTICAL, false);

        recyclerView=getActivity().findViewById(R.id.historyRecycler);
        recyclerView.setLayoutManager(mLayoutManager);
        /*adapter=new CurrentPresAdapter(getActivity().getApplicationContext(),list);
        recyclerView.setAdapter(adapter);*/

    }
    private void addAdapter() {
        list=new ArrayList<>();
        list.add(new CurrentMedicine("med1","Dose timings: X times a Day\n" +
                "Dose Strength: Y mg\n" +
                "Active ingredient : <Chemical_Name>\n" +
                "Description: <Info Dump>","taken"));
        list.add(new CurrentMedicine("med2","Dose timings: X times a Day\n" +
                "Dose Strength: Y mg\n" +
                "Active ingredient : <Chemical_Name>\n" +
                "Description: <Info Dump>","taken"));
        list.add(new CurrentMedicine("med3","Dose timings: X times a Day\n" +
                "Dose Strength: Y mg\n" +
                "Active ingredient : <Chemical_Name>\n" +
                "Description: <Info Dump>","taken"));
    }
}
