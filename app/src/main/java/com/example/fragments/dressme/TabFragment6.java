package com.example.fragments.dressme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.root.dressme.MainActivity;
import com.example.root.dressme.R;
import com.example.root.dressme.TagScreens2;

public class TabFragment6 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //View v = inflater.inflate(R.layout.fragment_tab1_fragment, container, false);
        View view = inflater.inflate(R.layout.fragment_tab_fragment6, container, false);

        ((TagScreens2)getActivity()).getSupportActionBar().setSubtitle("Select ocassion");

        Button elegant = (Button) view.findViewById(R.id.elegant);
        elegant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {gotonextscreen(view, R.id.elegant);}
        });
        Button casual = (Button) view.findViewById(R.id.casual);
        casual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {gotonextscreen(view, R.id.casual);}
        });

        return view;
    }

    public void gotonextscreen(View view, int id) {
        GradientDrawable shape3 = new GradientDrawable();
        shape3.setShape(GradientDrawable.RECTANGLE);
        shape3.setCornerRadii(new float[] { 35, 35, 35, 35, 35, 35, 35, 35 });
        shape3.setColor(Color.parseColor("#ffffff"));
        shape3.setStroke(5, Color.parseColor("#babbbb"));
        Button matt = (Button) view.findViewById(id);
        matt.setBackground(shape3);

        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
}
