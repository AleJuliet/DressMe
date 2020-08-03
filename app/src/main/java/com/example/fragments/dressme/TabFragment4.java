package com.example.fragments.dressme;

import android.content.Context;
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
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.root.dressme.R;
import com.example.root.dressme.TagScreens2;


public class TabFragment4 extends Fragment {
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
        View view = inflater.inflate(R.layout.fragment_tab_fragment4, container, false);

        ((TagScreens2)getActivity()).getSupportActionBar().setSubtitle("Select fabric");

        Button cotton = (Button) view.findViewById(R.id.cotton);
        cotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {gotonextscreen(view, R.id.cotton);}
        });
        Button wool = (Button) view.findViewById(R.id.wool);
        wool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {gotonextscreen(view, R.id.wool);}
        });
        Button linen = (Button) view.findViewById(R.id.linen);
        linen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {gotonextscreen(view, R.id.linen);}
        });
        Button silk = (Button) view.findViewById(R.id.silk);
        silk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {gotonextscreen(view, R.id.silk);}
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

        FragmentTransaction trans = getFragmentManager().beginTransaction();
        trans.replace(R.id.root_frame, new TabFragment5());
        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        trans.addToBackStack(null);

        trans.commit();
    }
}
