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
    @ColumnInfo(name = "t1")
    String t1;
    @ColumnInfo(name = "t2")
    String t2;
    @ColumnInfo(name = "t3")
    String t3;
    /*@ColumnInfo(name = "medicine4")
    String medicine4;
    @ColumnInfo(name = "medicine5")
    String medicine5;
    @ColumnInfo(name = "medicine6")
    String medicine6;
    @ColumnInfo(name = "t1")
    String t1;
    @ColumnInfo(name = "t2")
    String t2;
    @ColumnInfo(name = "t3")
    String t3;
    @ColumnInfo(name = "t4")
    String t4;
    @ColumnInfo(name = "t5")
    String t5;
    @ColumnInfo(name = "t6")
    String t6;*/

    /*public Prescription(@NonNull String name, String medicine1, String medicine2, String medicine3, String medicine4, String medicine5, String medicine6, String t1, String t2, String t3, String t4, String t5, String t6) {
        this.name = name;
        this.medicine1 = medicine1;
        this.medicine2 = medicine2;
        this.medicine3 = medicine3;
        this.medicine4 = medicine4;
        this.medicine5 = medicine5;
        this.medicine6 = medicine6;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
        this.t5 = t5;
        this.t6 = t6;
    }*/

    public Prescription(@NonNull String name, String t1, String t2, String t3) {
        this.name = name;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
    }
    /*public Prescription(String name, String medicine1, String medicine2, String medicine3, String medicine4, String medicine5, String medicine6) {
        this.name = name;
        this.medicine1 = medicine1;
        this.medicine2 = medicine2;
        this.medicine3 = medicine3;
        this.medicine4 = medicine4;
        this.medicine5 = medicine5;
        this.medicine6 = medicine6;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public String getT3() {
        return t3;
    }

    public void setT3(String t3) {
        this.t3 = t3;
    }
}

