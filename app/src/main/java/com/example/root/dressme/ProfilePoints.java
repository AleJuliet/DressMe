package com.example.root.dressme;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ProfilePoints extends AppCompatActivity {

    BarChart chart;

    public static final int[] test = {
            Color.rgb(0, 0, 0), Color.rgb(0, 140, 14), Color.rgb(24, 100, 227),
            Color.rgb(223, 20, 43), Color.rgb(42, 109, 130)
    };

    public static final int[] test2 = {
            Color.rgb(24, 100, 227), Color.rgb(0, 140, 14), Color.rgb(0, 0, 0),
            Color.rgb(223, 20, 43), Color.rgb(42, 109, 130)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_points);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        chart = (BarChart) findViewById(R.id.chart2);

        BarEntry stackedEntry = new BarEntry(0f, new float[] { 10, 20, 30 });
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, new float[] { 10, 20, 30 }));
        entries.add(new BarEntry(1f, new float[] { 20, 15, 10 }));
        entries.add(new BarEntry(2f, new float[] { 10, 0, 30 }));
        entries.add(new BarEntry(3f, new float[] { 0, 0, 0 }));
        entries.add(new BarEntry(4f, new float[] { 10, 0, 0 }));



        BarDataSet set = new BarDataSet(entries, "BarDataSet");

        final ArrayList<String> xLabel = new ArrayList<>();
        xLabel.add("Blue");
        xLabel.add("Black");
        xLabel.add("White");
        xLabel.add("Yellow");
        xLabel.add("Red");



        YAxis left = chart.getAxisLeft();
        left.setDrawGridLines(false);
        left.setDrawLabels(false);
        YAxis right = chart.getAxisRight();
        right.setDrawLabels(false);
        right.setDrawGridLines(false);
        right.setDrawZeroLine(true);
        right.setDrawTopYLabelEntry(true);
        right.setDrawAxisLine(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xLabel.get((int)value);
            }
        });

        BarDataSet set1;
        set1 = new BarDataSet(entries, "BarDataSet");
        set1.setDrawIcons(false);
        set1.setColors(getColors());
        set1.setDrawValues(false);
        //set1.setStackLabels(new String[]{"Blue", "White", "Black"});

        ArrayList<IBarDataSet> set2 = new ArrayList<IBarDataSet>();
        set2.add(set1);

        BarData data = new BarData(set2);
        data.setBarWidth(0.9f); // set custom bar width
        chart.setData(data);
        //Chart values
        chart.setDrawValueAboveBar(true);
        chart.getDescription().setEnabled(false);
        chart.setClickable(false);
        chart.getLegend().setEnabled(false);
        chart.setScaleEnabled(false);
        chart.setFitBars(true);

        chart.notifyDataSetChanged(); // let the chart know it's data changed
        chart.invalidate(); // refresh
    }

    private int[] getColors() {

        int stacksize = 3;

        // have as many colors as stack-values per entry
        int[] colors = new int[stacksize];

        for (int i = 0; i < stacksize; i++) {
            colors[i] = test[i];
        }

        return colors;
    }

}
