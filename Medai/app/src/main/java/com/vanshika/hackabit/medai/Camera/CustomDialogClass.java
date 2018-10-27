package com.vanshika.hackabit.medai.Camera;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.vanshika.hackabit.medai.Prescription.AddPrescription;
import com.vanshika.hackabit.medai.Prescription.PrescriptionList;
import com.vanshika.hackabit.medai.R;

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    private static String dose,info;
    //String dose,info;
    TextView medicineName, medicineInfo;
    public Activity c;
    public Dialog d;
    public Button yes, no;

    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }
    public static void addData(String dose1,String info1){
        dose=dose1;
        info=info1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        medicineName=findViewById(R.id.medicineName);
        medicineInfo=findViewById(R.id.medicineInfo);
        medicineInfo.setText(info);
        medicineName.setText(dose);
        yes = (Button) findViewById(R.id.btn_yes);
        //no = (Button) findViewById(R.id.btn_no);
        yes.setOnClickListener(this);
        //no.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_yes:
                Intent intent=new Intent(c, AddPrescription.class);
                intent.putExtra("name",dose);
                intent.putExtra("info",info);
                c.startActivity(intent);
                break;

            default:
                break;
        }
        dismiss();
    }
}
