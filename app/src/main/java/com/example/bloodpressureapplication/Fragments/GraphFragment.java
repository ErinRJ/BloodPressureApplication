package com.example.bloodpressureapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.example.bloodpressureapplication.server;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bloodpressureapplication.R;

import java.util.ArrayList;
import java.util.List;

public class GraphFragment extends Fragment {
    private AnyChartView anyChartView;
    private Cartesian cartesian;
    private String route, server_url;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View graph_view = inflater.inflate(R.layout.fragment_graph, null);
        anyChartView = graph_view.findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(graph_view.findViewById(R.id.progress_bar));
        Cartesian cartesian = AnyChart.line();
        cartesian.animation(true);
        cartesian.padding(10d, 20d, 5d, 20d);
        cartesian.crosshair().enabled(true);
        cartesian.crosshair().yLabel(true);
        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.title("Blood Pressure Trend");
        cartesian.yAxis(0).title("Date(Days)");
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

        List<DataEntry> seriesData = new ArrayList<>();
        seriesData.add(new CustomDataEntry("1986", 3.6, 2.3, 2.8));
        seriesData.add(new CustomDataEntry("1987", 7.1, 4.0, 4.1));



        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
        server_url = server.url;

        Line series1 = cartesian.line(series1Mapping);
        series1.name("Brandy");
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);


        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);

        anyChartView.setChart(cartesian);

        return graph_view;
    }

    private class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number value, Number value2, Number value3) {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
        }

    }
}
