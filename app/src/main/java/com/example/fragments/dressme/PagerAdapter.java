package com.example.fragments.dressme;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by root on 11/11/18.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    /*
    * Use to handler the fragments
     */

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new TabFragment1();
    }

    @Override
    public int getCount() {
        return 5;
    }
}
