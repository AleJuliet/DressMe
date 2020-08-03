package com.example.root.dressme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.dbhelper.room.Clothes;
import com.example.dbhelper.room.ClothesAdapter;
import com.example.dbhelper.room.ClothesDatabase;
import com.example.dbhelper.room.Outfit;
import com.example.dbhelper.room.OutfitLog;
import com.example.dbhelper.room.OutfitsAdapter;

import java.util.List;

public class LogScreen extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context mContext;

    private static ClothesDatabase clothesDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        clothesDatabase = ClothesDatabase
                .getDatabase(getApplicationContext());

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view2);

        List<OutfitLog> allcl = clothesDatabase.daoAccess().getAllOutfits();
        mAdapter = new OutfitsAdapter(allcl,this);
        mRecyclerView.setAdapter(mAdapter);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mContext = getApplicationContext();

        /*
        *Adding back arrow on the menu
         */
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
        super.onBackPressed();
    }

}
