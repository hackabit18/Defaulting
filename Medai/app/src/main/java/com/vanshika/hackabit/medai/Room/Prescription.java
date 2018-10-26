package com.vanshika.hackabit.medai.Room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "prescription")
public class Prescription {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "medicine1")
    String medicine1;
    @ColumnInfo(name = "medicine2")
    String medicine2;
    @ColumnInfo(name = "medicine3")
    String medicine3;
    @ColumnInfo(name = "medicine4")
    String medicine4;
    @ColumnInfo(name = "medicine5")
    String medicine5;
    @ColumnInfo(name = "medicine6")
    String medicine6;

    public Prescription(String name, String medicine1, String medicine2, String medicine3, String medicine4, String medicine5, String medicine6) {
        this.name = name;
        this.medicine1 = medicine1;
        this.medicine2 = medicine2;
        this.medicine3 = medicine3;
        this.medicine4 = medicine4;
        this.medicine5 = medicine5;
        this.medicine6 = medicine6;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMedicine1() {
        return medicine1;
    }

    public void setMedicine1(String medicine1) {
        this.medicine1 = medicine1;
    }

    public String getMedicine2() {
        return medicine2;
    }

    public void setMedicine2(String medicine2) {
        this.medicine2 = medicine2;
    }

    public String getMedicine3() {
        return medicine3;
    }

    public void setMedicine3(String medicine3) {
        this.medicine3 = medicine3;
    }

    public String getMedicine4() {
        return medicine4;
    }

    public void setMedicine4(String medicine4) {
        this.medicine4 = medicine4;
    }

    public String getMedicine5() {
        return medicine5;
    }

    public void setMedicine5(String medicine5) {
        this.medicine5 = medicine5;
    }

    public String getMedicine6() {
        return medicine6;
    }

    public void setMedicine6(String medicine6) {
        this.medicine6 = medicine6;
    }
}

