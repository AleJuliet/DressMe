package com.example.dbhelper.room;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

/**
 * Created by root on 10/18/18.
 */
@Database(entities = {Clothes.class, OutfitLog.class}, version = 3, exportSchema = false)
public abstract class ClothesDatabase extends RoomDatabase {
    public abstract ClothesDao daoAccess() ;
    private static ClothesDatabase INSTANCE;
    private static final String DATABASE_NAME = "clothes_database_alexa0";

    public static ClothesDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ClothesDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(),
                            ClothesDatabase.class, DATABASE_NAME)
                                    .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()   //Allows room to do operation on main thread
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
