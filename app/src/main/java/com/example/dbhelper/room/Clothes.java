package com.example.dbhelper.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;



/**
 * Created by root on 10/18/18.
 */
@Entity
public class Clothes {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String picturepath;
    public String type;
    public String color;
    public String ocassion;
    public String weather;


    public void setId(int uid) {
        this.id = uid;
    }

    public void setallcontent(String picture, String type, String color, String weather, String ocassion) {
        this.picturepath = picture;
        this.type = type;
        this.weather = weather;
        this.ocassion = ocassion;
        this.color = color;
    }

    public int getId() {
        return this.id;
    }

    public void setPath(String path) {
        this.picturepath = path;
    }

    public String getPath() {
        return this.picturepath;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeather() {
        return this.weather;
    }

    public void setOcassion(String ocassion) {
        this.ocassion = ocassion;
    }

    public String getOcassion() {
        return this.ocassion;
    }
}
