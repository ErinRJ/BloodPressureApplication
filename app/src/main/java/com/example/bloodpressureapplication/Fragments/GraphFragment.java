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

import java.util.ArrayList;
import java.util.List;

public class GraphFragment extends Fragment {
    private AnyChartView anyChartView;
    private Cartesian cartesian;
    private List<DataEntry> seriesData;
    private List<DataEntry> seriesData2;
    private RequestQueue queue;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View graph_view = inflater.inflate(R.layout.fragment_graph, null);
        anyChartView = graph_view.findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(graph_view.findViewById(R.id.progress_bar));
        cartesian = AnyChart.line();
        queue = Volley.newRequestQueue(getContext());



        final String url = server.url+"getBP"+"?clientID="+Person.clientID;
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        cartesian.animation(true);

                        cartesian.padding(10d, 20d, 5d, 20d);

                        cartesian.crosshair().enabled(true);
                        cartesian.crosshair()
                                .yLabel(true);

                        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

                        cartesian.title("Blood Pressure");
                        cartesian.yAxis(0).title("Diastolic/Systolic");
                        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

                        List<DataEntry> seriesData = new ArrayList<>();
//                        String response_split = response;
//                        response_split = response_split.substring(0, response_split.length() - 2);
//                        response_split = response_split.substring(2);
//                        String[] response_split_full = response_split.split("],\\[");
//                        for(int i = 0; i< response_split_full.length; i++) {
//                            String[] rsf_ss = response_split_full[i].split(",");
//                            seriesData.add(new CustomDataEntry(rsf_ss[0], Integer.parseInt(rsf_ss[1].replaceAll("[^a-zA-Z0-9]", "")), Integer.parseInt(rsf_ss[2].replaceAll("[^a-zA-Z0-9]", ""))));
//                        }
                        seriesData.add(new CustomDataEntry("1", 80, 23));
                        seriesData.add(new CustomDataEntry("2", 120, 40));
                        seriesData.add(new CustomDataEntry("5", 111, 62));
                        seriesData.add(new CustomDataEntry("8", 105, 58));
                        seriesData.add(new CustomDataEntry("11", 101, 70));
                        seriesData.add(new CustomDataEntry("14", 116, 39));
                        seriesData.add(new CustomDataEntry("15", 164, 80));
                        seriesData.add(new CustomDataEntry("16", 180, 33));

                        Set set = Set.instantiate();
                        set.data(seriesData);
                        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
                        Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }");

                        Line series1 = cartesian.line(series1Mapping);
                        series1.name("Diastolic");
                        series1.hovered().markers().enabled(true);
                        series1.hovered().markers()
                                .type(MarkerType.CIRCLE)
                                .size(4d);
                        series1.tooltip()
                                .position("right")
                                .anchor(Anchor.LEFT_CENTER)
                                .offsetX(5d)
                                .offsetY(5d);

                        Line series2 = cartesian.line(series2Mapping);
                        series2.name("Systolic");
                        series2.hovered().markers().enabled(true);
                        series2.hovered().markers()
                                .type(MarkerType.CIRCLE)
                                .size(4d);
                        series2.tooltip()
                                .position("right")
                                .anchor(Anchor.LEFT_CENTER)
                                .offsetX(5d)
                                .offsetY(5d);

                        cartesian.legend().enabled(true);
                        cartesian.legend().fontSize(13d);
                        cartesian.legend().padding(0d, 0d, 10d, 0d);

                        anyChartView.setChart(cartesian);
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


    private class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number value, Number value2) {
            super(x, value);
            setValue("value2", value2);
        }

    }
}
