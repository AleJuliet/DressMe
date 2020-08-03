package com.example.dbhelper.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;



/**
 * Created by root on 10/18/18.
 */
@Dao
public interface ClothesDao {

    @Query("SELECT * FROM clothes LIMIT 1")
    List<Clothes> getfirst();

    @Query("SELECT * FROM clothes")
    List<Clothes> getAll();

    @Query("SELECT * FROM clothes where id=3")
    Clothes getFirst();

    @Query("SELECT * FROM clothes where id=1")
    Clothes getPants();

    @Query("SELECT * FROM clothes where type='top'")
    List<Clothes> getAllTops();

    @Query("SELECT * FROM clothes where type='bottom'")
    List<Clothes> getAllPants();

    @Insert
    void insertAll(Clothes... users);

    @Delete
    void delete(Clothes user);

    @Query("DELETE FROM clothes")
    public void nukeTable();

    @Query("DELETE FROM clothes")
    public void nukeTable2();

    @Query("SELECT * FROM OutfitLog LIMIT 1")
    OutfitLog getfirstOutfit();

    @Insert
    void insertOutfit(OutfitLog... outfits);

    @Query("SELECT * FROM OutfitLog order by id desc")
    List<OutfitLog> getAllOutfits();
}
