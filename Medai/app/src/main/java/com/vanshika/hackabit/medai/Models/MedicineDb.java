package com.vanshika.hackabit.medai.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MedicineDb {
    @SerializedName("drug-name")
    @Expose
    String medicineName;
    @SerializedName("side-effects")
    String sideEffects;
    @SerializedName("dosage")
    String dosage;
    @SerializedName("when-ntb-taken")
    String ntb;

    /*public MedicineDb(String medicineName, String medicineInfo) {
        this.medicineName = medicineName;
        this.medicineInfo = medicineInfo;
    }*/

    public MedicineDb(String medicineName, String sideEffects, String dosage, String ntb) {
        this.medicineName = medicineName;
        this.sideEffects = sideEffects;
        this.dosage = dosage;
        this.ntb = ntb;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    /*public String getMedicineInfo() {
        return medicineInfo;
    }

    public void setMedicineInfo(String medicineInfo) {
        this.medicineInfo = medicineInfo;
    }*/

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getNtb() {
        return ntb;
    }

    public void setNtb(String ntb) {
        this.ntb = ntb;
    }
}
