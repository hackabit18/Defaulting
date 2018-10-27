package com.vanshika.hackabit.medai.Room;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.vanshika.hackabit.medai.Prescription.PrescriptionList;

@Database(entities = {Prescription.class,NewDose.class},version=2)
public abstract
class AppDatabase  extends RoomDatabase{
    private static AppDatabase INSTANCE;

    public abstract UserDao userDao();
    public static AppDatabase getAppDatabase(Context context){
        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"new_dose")
                    .allowMainThreadQueries()
                    .build();
        }return INSTANCE;
    }
}
