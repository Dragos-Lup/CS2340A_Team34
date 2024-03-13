package com.example.cs2340ateam34;


import com.anychart.AnyChart;
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

public class ChartHandler {

    public static Cartesian makeGraph(boolean calorieOrPrice) { // False for calories, true for price
        User user = User.getInstance();
        Cartesian cartesian = AnyChart.column();
        List<DataEntry> data = new ArrayList<>();
        ArrayList<Meal> meals = user.getMealList();
        int mealsIndex = meals.size() - 1;
        while(data.size() < 7 && mealsIndex >= 0) {
            Meal meal = meals.get(mealsIndex--);
            data.add(new ValueDataEntry(meal.getMealName(), calorieOrPrice ? meal.getPrice() : meal.getCalories()));
        }

        Column column = cartesian.column(data);

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("${%Value}{groupsSeparator: }");

        cartesian.animation(true);
        cartesian.title("Most Recent Meal " + (calorieOrPrice ? "Prices" : "Calorie Counts"));

        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("${%Value}{groupsSeparator: }");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Meal");
        cartesian.yAxis(0).title(calorieOrPrice? "Price" : "Calories");

        return cartesian;
    }
}
