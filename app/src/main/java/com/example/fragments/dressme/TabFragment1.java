package com.example.fragments.dressme;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.root.dressme.R;
import com.example.root.dressme.TagScreens2;

/**
 * Created by root on 11/11/18.
 */

public class TabFragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //View v = inflater.inflate(R.layout.fragment_tab1_fragment, container, false);
        View view = inflater.inflate(R.layout.fragment_tab1_fragment, container, false);

        ((TagScreens2)getActivity()).getSupportActionBar().setSubtitle("Select type of item");

        ImageView imgFavorite = (ImageView) view.findViewById(R.id.top);
        imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GradientDrawable shape2 = new GradientDrawable();
                shape2.setShape(GradientDrawable.RECTANGLE);
                shape2.setCornerRadii(new float[] { 35, 35, 35, 35, 35, 35, 35, 35 });
                shape2.setStroke(2, Color.parseColor("#602d8f"));
                shape2.setColor(Color.parseColor("#8389a5"));

                FrameLayout topf = (FrameLayout) view.findViewById(R.id.topf);
                topf.setBackground(shape2);

                FragmentTransaction trans = getFragmentManager().beginTransaction();
                /*
                 * IMPORTANT: We use the "root frame" defined in
                 * "root_fragment.xml" as the reference to replace fragment
                 */
                trans.replace(R.id.root_frame, new TabFragment2());

                /*
                 * IMPORTANT: The following lines allow us to add the fragment
                 * to the stack and return to it later, by pressing back
                 */
                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.addToBackStack(null);

                trans.commit();
            }
        });

        ImageView pants = (ImageView) view.findViewById(R.id.pants);
        pants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GradientDrawable shape2 = new GradientDrawable();
                shape2.setShape(GradientDrawable.RECTANGLE);
                shape2.setCornerRadii(new float[] { 35, 35, 35, 35, 35, 35, 35, 35 });
                shape2.setStroke(2, Color.parseColor("#602d8f"));
                shape2.setColor(Color.parseColor("#8389a5"));

                FrameLayout pantsf = (FrameLayout) view.findViewById(R.id.pantsf);
                pantsf.setBackground(shape2);

                FragmentTransaction trans = getFragmentManager().beginTransaction();
                trans.replace(R.id.root_frame, new TabFragment2());
                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.addToBackStack(null);

                trans.commit();
            }
        });

        return view;
    }


}
