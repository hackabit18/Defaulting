package com.vanshika.hackabit.medai.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vanshika.hackabit.medai.Models.CurrentMedicine;
import com.vanshika.hackabit.medai.Models.MedicineDb;
import com.vanshika.hackabit.medai.R;
import com.vanshika.hackabit.medai.Room.NewDose;

import java.util.ArrayList;
import java.util.List;

public class CurrentPresAdapter extends RecyclerView.Adapter<CurrentPresAdapter.listViewHolder>{
    private Context context;
    private List<NewDose> list;

    public CurrentPresAdapter(Context context, List<NewDose> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public listViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        listViewHolder holder =
                new listViewHolder(LayoutInflater.from(context).inflate(R.layout.card_current_dose, parent, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull listViewHolder holder, int position) {
        NewDose object=list.get(position);
        holder.doseName.setText(object.getName());
        holder.doseSide.setText(object.getDosage());
        holder.ntb.setText(object.getNotTaken());
        holder.t1.setText(object.getT1());
        holder.t2.setText(object.getT2());
        holder.t3.setText(object.getT3());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class listViewHolder extends RecyclerView.ViewHolder {
        public TextView doseName,doseSide,ntb,t1,t2,t3;

        public listViewHolder(View itemView) {
            super(itemView);

            doseName = (TextView) itemView.findViewById(R.id.doseName);
            doseSide = (TextView) itemView.findViewById(R.id.doseSideEffect);
            ntb=(TextView) itemView.findViewById(R.id.ntb);
            t1=(TextView) itemView.findViewById(R.id.t1);
            t2=(TextView) itemView.findViewById(R.id.t2);
            t3=(TextView) itemView.findViewById(R.id.t3);

        }
    }
}
