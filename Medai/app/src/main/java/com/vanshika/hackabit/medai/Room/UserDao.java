package com.vanshika.hackabit.medai.Room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import java.util.ArrayList;
import java.util.List;

@Dao
public interface UserDao {
    /*@Query("SELECT * FROM prescription")
    List<Prescription> getAllPrescriptions();*/
    /*@Insert
    void insertAll(Prescription prescriptionObject);*/
    @Insert
    void inserNewDose(NewDose newDose);

    @Query("SELECT * FROM newdose where fragment = 0")
    List<NewDose> getNewDose();

    @Query("select count(*) from newdose")
    int returnCount();

    @Query("UPDATE newdose SET fragment= 1 WHERE `drug-name` = :frag")
    void updateTable(String frag);

    /*@Query("INSERT INTO olddose ")
    void insertOldDose(NewDose historyDose);*/
    @Query("select * from newdose WHERE fragment=1")
    List<NewDose> getHistoryDose();
}
