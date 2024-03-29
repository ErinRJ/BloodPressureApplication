package com.example.bloodpressureapplication.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bloodpressureapplication.NewMedicationActivity;
import com.example.bloodpressureapplication.R;
import com.example.bloodpressureapplication.server;


import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class MedicationFragment extends Fragment {
    ListView medsLV;
    RequestQueue queue;
    String medications = "";
    String meds;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Setting values
        View view = inflater.inflate(R.layout.fragment_meds, null);
        medsLV = (ListView) view.findViewById(R.id.medsLV);
        queue = Volley.newRequestQueue(getActivity().getApplicationContext());

        //Obtaining data from database and place it in an array adaptor
        getMedications();

        return view;
    }

    public void getMedications(){
        final String url = server.url+"getMedNames?id=1";
        Log.d("URL", url);

        // prepare the Request
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                        medications = response;

                        ArrayList<Medication> listOfMeds = new ArrayList<>();

                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(medications);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String[] strArr = new String[jsonArray.length()];

                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                strArr[i] = jsonArray.getString(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        Log.d("Response array index 0", strArr[0]);

                        for (String s: strArr) {
                            Log.d("Response string s", s);
                            Medication med1 = new Medication(s, "12:00", "5", "asdf","Monday", true);
                            listOfMeds.add(med1);
                        }

                        //Place data in Array Adaptor
                        MedListAdapter medAdapter = new MedListAdapter(getContext(), R.layout.med_layout, listOfMeds);

                        medsLV.setAdapter(medAdapter);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", String.valueOf(error));
                    }
                }
        );

        // add it to the RequestQueue
        queue.add(getRequest);
    }

}
