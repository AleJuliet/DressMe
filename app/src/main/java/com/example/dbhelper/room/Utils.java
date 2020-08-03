package com.example.dbhelper.room;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.min;

/**
 * Created by root on 10/20/18.
 */

public class Utils {

    private static final String TAG = "Utils";

    private static ClothesDatabase clothesDatabase;

    public static List<Outfit> loadProfiles(Context context){
        clothesDatabase = ClothesDatabase
                .getDatabase(context.getApplicationContext());
        try{
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            JSONArray array = new JSONArray(loadJSONFromAsset(context, "profiles.json"));

            List<Clothes> allcl = clothesDatabase.daoAccess().getAllTops();
            Collections.shuffle(allcl);

            List<Clothes> allcl2 = clothesDatabase.daoAccess().getAllPants();
            List<Outfit> alloutfits = new ArrayList<>();

            int nmoutfits = min(allcl.size(),allcl2.size());

            for(int i=0;i<allcl2.size();i++){
                Clothes c22 = allcl2.get(i);
            }

            for(int i=0;i<nmoutfits;i++){
                Clothes c1 = allcl.get(i);
                Clothes c2 = allcl2.get(i);

                Outfit outfit = new Outfit(c1.getPath(), c2.getPath(),c1.getId());
                alloutfits.add(outfit);
            }

            return alloutfits;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private static String loadJSONFromAsset(Context context, String jsonFileName) {
        String json = null;
        InputStream is=null;
        try {
            AssetManager manager = context.getAssets();
            Log.d(TAG,"path "+jsonFileName);
            is = manager.open(jsonFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
