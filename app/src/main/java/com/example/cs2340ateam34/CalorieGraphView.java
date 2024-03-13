package com.example.cs2340ateam34;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;

import java.util.ArrayList;
import java.util.List;

public class CalorieGraphView extends Fragment {

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.calorie_graph, container, false);
        AnyChartView anyChartView = view.findViewById(R.id.cal_graph);

        User user = User.getInstance();
        Cartesian cartesian = AnyChart.column();

        List<DataEntry> data = new ArrayList<>();
        ArrayList<Meal> meals = user.getMealList();
        int mealsIndex = meals.size() - 1;
        while(data.size() < 7 && mealsIndex >= 0) {
            Meal meal = meals.get(mealsIndex--);
            data.add(new ValueDataEntry(meal.getMealName(), meal.getCalories()));
        }

        Column column = cartesian.column(data);

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("{%Value}{groupsSeparator: }");

        cartesian.animation(true);
        cartesian.title("Calorie Graph");

        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Meals");
        cartesian.yAxis(0).title("Calories");

        anyChartView.setChart(cartesian);

        return view;
    }
}
