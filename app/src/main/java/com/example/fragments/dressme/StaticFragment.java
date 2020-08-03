package com.example.fragments.dressme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.dressme.R;

/**
 * Created by root on 11/11/18.
 */

public class StaticFragment extends Fragment {

    private static final String TAG = "StaticFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater
                .inflate(R.layout.static_fragment2, container, false);

        return view;
    }

}
