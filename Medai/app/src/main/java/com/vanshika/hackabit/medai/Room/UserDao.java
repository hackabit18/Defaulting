package com.vanshika.hackabit.medai.Room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;



import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM prescription")
    List<Prescription> getAllPrescriptions();
    @Insert
    void insertAll(Prescription... prescriptionLists);
}
