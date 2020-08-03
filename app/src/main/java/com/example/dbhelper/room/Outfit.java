package com.example.dbhelper.room;

import android.content.Context;

import com.mindorks.placeholderview.SwipePlaceHolderView;

/**
 * Created by root on 10/20/18.
 */
public class Outfit {

    private String imageTop;
    private String imageBottom;
    private int idclothes;

    public Outfit(String top, String bottom, int idclo) {
        this.imageTop = top;
        this.imageBottom = bottom;
        this.idclothes = idclo;
    }

    public String getTop() {
        return imageTop;
    }

    public void setTop(String name) {
        this.imageTop = name;
    }

    public String getBottom() {
        return imageBottom;
    }

    public void setBottom(String name) {
        this.imageBottom = name;
    }

    public int getIdC() {
        return idclothes;
    }

    public void setIdCl(int idc) {
        this.idclothes = idc;
    }
}
