package com.example.dbhelper.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.sql.Time;
import java.util.Date;


/**
 * Created by root on 11/4/18.
 */

@Entity
public class OutfitLog {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String top;
    public String bottom;
    public int idoutfit;

    @ColumnInfo(name = "created_date")
    @TypeConverters({TimestampConverter.class})
    public Date date2;

    public void setId(int uid) {
        this.id = uid;
    }

    public void setallcontent(String top, String bottom, Date datelog, int idoutfit) {
        this.top = top;
        this.bottom = bottom;
        this.date2 = datelog;
    }

    public int getId() {
        return this.id;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getTop() {
        return this.top;
    }

    public void setBottom(String bottom) {
        this.bottom = bottom;
    }

    public String getBottom() {
        return this.bottom;
    }

    public void setDate(Date datelog) {
        this.date2 = datelog;
    }

    public Date getDate() {
        return this.date2;
    }

}
