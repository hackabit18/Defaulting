package com.vanshika.hackabit.medai.Room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "new_dose")
public class NewDose {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "drug-name")
    String name;
    @ColumnInfo(name = "side-effects")
    String side_effects;
    @ColumnInfo(name = "dosage")
    String dosage;
    @ColumnInfo(name = "when-ntb-taken")
    String notTaken;
    @ColumnInfo(name = "t1")
    String t1;
    @ColumnInfo(name = "t2")
    String t2;
    @ColumnInfo(name = "t3")
    String t3;

    public NewDose(@NonNull String name, String side_effects, String dosage, String notTaken, String t1, String t2, String t3) {
        this.name = name;
        this.side_effects = side_effects;
        this.dosage = dosage;
        this.notTaken = notTaken;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
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
    /*public NewDose(@NonNull String name, String side_effects, String dosage, String notTaken) {
        this.name = name;
        this.side_effects = side_effects;
        this.dosage = dosage;
        this.notTaken = notTaken;
    }*/

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getSide_effects() {
        return side_effects;
    }

    public void setSide_effects(String side_effects) {
        this.side_effects = side_effects;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getNotTaken() {
        return notTaken;
    }

    public void setNotTaken(String notTaken) {
        this.notTaken = notTaken;
    }
}
