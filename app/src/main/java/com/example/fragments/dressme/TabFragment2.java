package com.example.fragments.dressme;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.root.dressme.R;
import com.example.root.dressme.TagScreens2;

/**
 * Created by root on 11/11/18.
 */

public class TabFragment2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //View v = inflater.inflate(R.layout.fragment_tab1_fragment, container, false);
        View view = inflater.inflate(R.layout.fragment_tab2_fragment, container, false);

        ((TagScreens2)getActivity()).getSupportActionBar().setSubtitle("Select color");
        FrameLayout color1 = (FrameLayout) view.findViewById(R.id.black);
        color1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {gotonextscreen();}
        });
        FrameLayout color2 = (FrameLayout) view.findViewById(R.id.white);
        color2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {gotonextscreen();}
        });
        FrameLayout color3 = (FrameLayout) view.findViewById(R.id.gray);
        color3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {gotonextscreen();}
        });
        FrameLayout color4 = (FrameLayout) view.findViewById(R.id.yellow);
        color4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {gotonextscreen();}
        });
        FrameLayout color5 = (FrameLayout) view.findViewById(R.id.pink);
        color5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {gotonextscreen();}
        });
        FrameLayout color6 = (FrameLayout) view.findViewById(R.id.brown);
        color6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {gotonextscreen();}
        });
        FrameLayout color7 = (FrameLayout) view.findViewById(R.id.purple);
        color7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {gotonextscreen();}
        });
        FrameLayout color8 = (FrameLayout) view.findViewById(R.id.green);
        color8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {gotonextscreen();}
        });
        FrameLayout color9 = (FrameLayout) view.findViewById(R.id.blue);
        color9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {gotonextscreen();}
        });
        FrameLayout color = (FrameLayout) view.findViewById(R.id.blue);
        color9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {gotonextscreen();}
        });

        return view;
    }

    public void gotonextscreen() {
        /*GradientDrawable shape2 = new GradientDrawable();
        shape2.setShape(GradientDrawable.RECTANGLE);
        shape2.setCornerRadii(new float[] { 35, 35, 35, 35, 35, 35, 35, 35 });
        shape2.setStroke(2, Color.parseColor("#602d8f"));
        shape2.setColor(Color.parseColor("#8389a5"));
        FrameLayout pantsf = (FrameLayout) view.findViewById(R.id.pantsf);
        pantsf.setBackground(shape2);*/

        FragmentTransaction trans = getFragmentManager().beginTransaction();
        trans.replace(R.id.root_frame, new TabFragment3());
        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        trans.addToBackStack(null);

        trans.commit();
    }

}
