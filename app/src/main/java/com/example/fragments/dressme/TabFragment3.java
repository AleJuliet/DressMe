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
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.root.dressme.R;
import com.example.root.dressme.TagScreens2;

public class TabFragment3 extends Fragment {
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
        View view = inflater.inflate(R.layout.fragment_tab_fragment3, container, false);

        ((TagScreens2)getActivity()).getSupportActionBar().setSubtitle("Select weather");

        ImageView cold = (ImageView) view.findViewById(R.id.cold);
        cold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {gotonextscreen(view, R.id.coldf);}
        });

        ImageView warm = (ImageView) view.findViewById(R.id.warm);
        warm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {gotonextscreen(view, R.id.warmf);}
        });

        ImageView allw = (ImageView) view.findViewById(R.id.allw);
        allw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {gotonextscreen(view, R.id.allwf);}
        });

        return view;
    }

    public void gotonextscreen(View view, int id) {
        GradientDrawable shape2 = new GradientDrawable();
        shape2.setShape(GradientDrawable.RECTANGLE);
        shape2.setCornerRadii(new float[] { 35, 35, 35, 35, 35, 35, 35, 35 });
        shape2.setStroke(2, Color.parseColor("#602d8f"));
        shape2.setColor(Color.parseColor("#8389a5"));
        FrameLayout pantsf = (FrameLayout) view.findViewById(id);
        pantsf.setBackground(shape2);

        FragmentTransaction trans = getFragmentManager().beginTransaction();
        trans.replace(R.id.root_frame, new TabFragment4());
        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        trans.addToBackStack(null);

        trans.commit();
    }
}
