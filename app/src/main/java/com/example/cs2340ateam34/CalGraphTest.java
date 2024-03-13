package com.example.cs2340ateam34;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;

import java.util.ArrayList;
import java.util.List;

public class CalGraphTest extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calorie_graph);
        Pie pie = AnyChart.pie();

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("John", 10000));
        data.add(new ValueDataEntry("Jake", 12000));
        data.add(new ValueDataEntry("Peter", 18000));

        AnyChartView anyChartView = (AnyChartView) findViewById(R.id.cal_graph);
        anyChartView.setChart(pie);
//        AnyChartView calGraphView = findViewById(R.id.cal_graph);
//
//        User user = User.getInstance();
//
//
//        Cartesian cartesian = AnyChart.column();
//
//        List<DataEntry> data = new ArrayList<>();
//        data.add(new ValueDataEntry("Rouge", 80540));
//        data.add(new ValueDataEntry("Foundation", 94190));
//        data.add(new ValueDataEntry("Mascara", 102610));
//        data.add(new ValueDataEntry("Lip gloss", 110430));
//        data.add(new ValueDataEntry("Lipstick", 128000));
//        data.add(new ValueDataEntry("Nail polish", 143760));
//        data.add(new ValueDataEntry("Eyebrow pencil", 170670));
//        data.add(new ValueDataEntry("Eyeliner", 213210));
//        data.add(new ValueDataEntry("Eyeshadows", 249980));
//
//        Column column = cartesian.column(data);
//
//        column.tooltip()
//                .titleFormat("{%X}")
//                .position(Position.CENTER_BOTTOM)
//                .anchor(Anchor.CENTER_BOTTOM)
//                .offsetX(0d)
//                .offsetY(5d)
//                .format("${%Value}{groupsSeparator: }");
//
//        cartesian.animation(true);
//        cartesian.title("Top 10 Cosmetic Products by Revenue");
//
//        cartesian.yScale().minimum(0d);
//
//        cartesian.yAxis(0).labels().format("${%Value}{groupsSeparator: }");
//
//        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
//        cartesian.interactivity().hoverMode(HoverMode.BY_X);
//
//        cartesian.xAxis(0).title("Product");
//        cartesian.yAxis(0).title("Revenue");
//
//        calGraphView.setChart(cartesian);
//        Log.d("Test graph load", "Hello");
    }
}
