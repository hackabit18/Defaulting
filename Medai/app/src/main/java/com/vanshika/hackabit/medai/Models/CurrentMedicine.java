package com.vanshika.hackabit.medai.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentMedicine {

    String medicineName;

    String medicineInfo;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    String time;

    public CurrentMedicine(String medicineName, String medicineInfo, String time) {
        this.medicineName = medicineName;
        this.medicineInfo = medicineInfo;
        this.time = time;
    }

   /* public CurrentMedicine(String medicineName, String medicineInfo) {
        this.medicineName = medicineName;
        this.medicineInfo = medicineInfo;
    }*/

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineInfo() {
        return medicineInfo;
    }

    public void setMedicineInfo(String medicineInfo) {
        this.medicineInfo = medicineInfo;
    }

}
