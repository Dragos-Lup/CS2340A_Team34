package com.example.cs2340ateam34;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


import com.anychart.AnyChartView;
public class CalorieGraphView extends Fragment {

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.calorie_graph, container, false);
        AnyChartView anyChartView = view.findViewById(R.id.cal_graph);

        GraphCreator calGrapher = new CalorieGraphCreator();

        anyChartView.setChart(calGrapher.makeGraph());

        return view;
    }
}
