package com.example.bloodpressureapplication.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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
import com.example.bloodpressureapplication.Person;
import com.google.gson.Gson;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bloodpressureapplication.R;

import org.json.JSONObject;

import java.util.List;

public class GraphFragment extends Fragment {
    private AnyChartView anyChartView;
    private Cartesian cartesian;
    private List<DataEntry> seriesData;
    private RequestQueue queue;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View graph_view = inflater.inflate(R.layout.fragment_graph, null);
        anyChartView = graph_view.findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(graph_view.findViewById(R.id.progress_bar));

        queue = Volley.newRequestQueue(getContext());

        Cartesian cartesian = AnyChart.line();
        cartesian.animation(true);
        cartesian.padding(10d, 20d, 5d, 20d);
        cartesian.crosshair().enabled(true);
        cartesian.crosshair().yLabel(true);
        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.title("Blood Pressure Trend");
        cartesian.yAxis(0).title("Date(Days)");
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

        final String url = server.url+"getBP"+"?email="+Person.email;
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseAllData(response, seriesData);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.response", String.valueOf(error));
                    }
                });

        queue.add(getRequest);
        return graph_view;
    }

    private void parseAllData(String response, List<DataEntry> bp_list) {
       Log.d("Res", response);
        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
        

        // Draw Series Line
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

        // Legend initialize
        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);

        ///////////TEMP-ADDS////////////////
        seriesData.add(new CustomDataEntry("1986", 3));
        seriesData.add(new CustomDataEntry("1987", 7.1));
        ///////////////////////////////////


        anyChartView.setChart(cartesian);
    }

    private class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number value) {
            super(x, value);
        }

    }
}
