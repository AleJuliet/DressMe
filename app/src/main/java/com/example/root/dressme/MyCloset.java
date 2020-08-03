package com.example.root.dressme;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.dbhelper.room.Clothes;
import com.example.dbhelper.room.ClothesAdapter;
import com.example.dbhelper.room.ClothesDatabase;

import java.util.List;

public class MyCloset extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private static ClothesDatabase clothesDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_closet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        clothesDatabase = ClothesDatabase
                .getDatabase(getApplicationContext());


        List<Clothes> allcl = clothesDatabase.daoAccess().getAll();
        mAdapter = new ClothesAdapter(allcl,this);
        mRecyclerView.setAdapter(mAdapter);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(MyCloset.this, MainActivity.class);
                    MyCloset.this.startActivity(intent);
                    return true;
                case R.id.navigation_dashboard:
                    Intent intent2 = new Intent(MyCloset.this, AddPhotoTagsActivity.class);
                    MyCloset.this.startActivity(intent2);
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };

}
