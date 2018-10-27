package com.vanshika.hackabit.medai.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MedicineDb {
    @SerializedName("name")
    @Expose
    String medicineName;
    @SerializedName("info")
    String medicineInfo;

    public MedicineDb(String medicineName, String medicineInfo) {
        this.medicineName = medicineName;
        this.medicineInfo = medicineInfo;
    }

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
