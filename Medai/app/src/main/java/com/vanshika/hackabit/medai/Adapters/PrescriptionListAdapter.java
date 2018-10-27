package com.vanshika.hackabit.medai.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vanshika.hackabit.medai.Prescription.PrescriptionActivity;
import com.vanshika.hackabit.medai.R;
import com.vanshika.hackabit.medai.Room.Prescription;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionListAdapter extends RecyclerView.Adapter<PrescriptionListAdapter.listViewHolder> {
    private Context context;
    private List<Prescription> prescriptionList;

    public PrescriptionListAdapter(Context context, List<Prescription> prescriptionList) {
        this.context = context;
        this.prescriptionList = prescriptionList;
    }

    @NonNull
    @Override
    public listViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        listViewHolder holder =
                new listViewHolder(LayoutInflater.from(context).inflate(R.layout.list_prescription, parent, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull listViewHolder holder, int position) {
        Prescription prescriptionName=prescriptionList.get(position);
        holder.topic.setText(prescriptionName.getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, PrescriptionActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return prescriptionList.size();
    }

    class listViewHolder extends RecyclerView.ViewHolder {
        public TextView topic;
        CardView cardView;

        public listViewHolder(View itemView) {
            super(itemView);

            topic = (TextView) itemView.findViewById(R.id.title);
            cardView = (CardView) itemView.findViewById(R.id.cardView);

        }
    }
}
