package com.vanshika.hackabit.medai.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vanshika.hackabit.medai.Fragments.CurrentDoseFragment;
import com.vanshika.hackabit.medai.Fragments.HistoryFragment;
import com.vanshika.hackabit.medai.Models.CurrentMedicine;
import com.vanshika.hackabit.medai.Models.MedicineDb;
import com.vanshika.hackabit.medai.R;
import com.vanshika.hackabit.medai.Room.AppDatabase;
import com.vanshika.hackabit.medai.Room.HistoryDose;
import com.vanshika.hackabit.medai.Room.NewDose;

import java.util.ArrayList;
import java.util.List;

public class CurrentPresAdapter extends RecyclerView.Adapter<CurrentPresAdapter.listViewHolder> {
    private Context context;
    private List<NewDose> list;
    private List<NewDose> list1;
    //private List<>
    private String frag;
    AppDatabase db;

    public CurrentPresAdapter(Context context, List<NewDose> list, String frag) {
        this.context = context;
        if (frag == "current") {
            this.list = list;
        } else {
            list1 = list;
        }
        this.frag = frag;
    }

    @NonNull
    @Override
    public listViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        listViewHolder holder =
                new listViewHolder(LayoutInflater.from(context).inflate(R.layout.card_current_dose, parent, false));
        db = AppDatabase.getAppDatabase(context);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final listViewHolder holder, int position) {
        final NewDose object;
        if (frag == "current") {
            object = list.get(position);
        } else {
            object = list1.get(position);

        }
        final int i = position;
        holder.doseName.setText(object.getName());
        holder.doseSide.setText(object.getDosage());
        holder.ntb.setText(object.getNotTaken());
        holder.t1.setText(object.getT1());
        holder.t2.setText(object.getT2());
        holder.t3.setText(object.getT3());
        if (frag.equals("current")) {
            holder.taken.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //db.userDao().insertOldDose(new NewDose(object.getName(),object.getDosage(),object.getSide_effects(),object.getNotTaken(),object.getT1(),object.getT2(),object.getT3()));
                    /*Log.v("adapterline62",db.userDao().getHistoryDose().get(db.userDao().getHistoryDose().size()-1).getName()+" "+db.userDao().getHistoryDose().size());
                    Toast.makeText(context, "Dose Removed", Toast.LENGTH_SHORT).show();*/
                    db.userDao().updateTable(object.getName());
                    holder.cardView.removeViewAt(i);
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (frag == "current") {
            return list.size();

        }
        return list1.size();

    }

    class listViewHolder extends RecyclerView.ViewHolder {
        public TextView doseName, doseSide, ntb, t1, t2, t3;
        Button taken, cancel;
        CardView cardView;

        public listViewHolder(View itemView) {
            super(itemView);

            doseName = (TextView) itemView.findViewById(R.id.doseName);
            doseSide = (TextView) itemView.findViewById(R.id.doseSideEffect);
            ntb = (TextView) itemView.findViewById(R.id.ntb);
            t1 = (TextView) itemView.findViewById(R.id.t1);
            t2 = (TextView) itemView.findViewById(R.id.t2);
            t3 = (TextView) itemView.findViewById(R.id.t3);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            taken = (Button) itemView.findViewById(R.id.taken);
            cancel = (Button) itemView.findViewById(R.id.cancel);
            if (frag.equals("old")) {
                taken.setVisibility(View.GONE);
                cancel.setVisibility(View.GONE);
            }
        }
    }
}
