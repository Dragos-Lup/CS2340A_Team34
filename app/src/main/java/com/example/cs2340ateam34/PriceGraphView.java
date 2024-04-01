package com.example.cs2340ateam34;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import com.anychart.AnyChartView;

public class PriceGraphView extends Fragment {

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.price_graph, container, false);
        AnyChartView anyChartView = view.findViewById(R.id.prc_graph);

        GraphCreator priceGrapher = new PriceGraphCreator();

        anyChartView.setChart(priceGrapher.makeGraph());

        return view;
    }
}
