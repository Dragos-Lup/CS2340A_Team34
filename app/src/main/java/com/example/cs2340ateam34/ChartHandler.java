package com.example.cs2340ateam34;


import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.Position;

import java.util.ArrayList;
import java.util.List;

public class ChartHandler {

    public static Cartesian makeCalorieGraph() {
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
                .format("${%Value}{groupsSeparator: }");

        return null;
    }
}
