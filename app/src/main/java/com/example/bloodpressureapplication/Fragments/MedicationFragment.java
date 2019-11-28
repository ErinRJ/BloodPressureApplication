package com.example.bloodpressureapplication.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

public class MedicationFragment extends Fragment {
    ListView medsLV;
    RequestQueue queue;

    // TODO: need to get id at login so we can use it here
    String clientId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Setting values
        View view = inflater.inflate(R.layout.fragment_meds, null);
        queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        medsLV = (ListView) view.findViewById(R.id.medsLV);
        clientId = "1";

        getMedications();

        //Obtaining data from database and place it in an array adaptor
        ArrayList<Medication> listOfMeds = new ArrayList<>();

        /////////////////////////////////////////// TO BE DELETED /////////////////////////////////////////////////
        Medication med1 = new Medication("medName1", "12:00", "5", "asdf","Monday", true);
        Medication med2 = new Medication("medName2", "12:00", "5", "asdf", "Monday",true);
        Medication med3 = new Medication("medName3", "12:00", "5", "asdf","Monday",true);
        Medication med4 = new Medication("medName4", "12:00", "5", "asdf","Monday",true);
        Medication med6 = new Medication("medName6", "12:00", "5", "asdf","Monday",true);
        listOfMeds.add(med1);
        listOfMeds.add(med2);
        listOfMeds.add(med3);
        listOfMeds.add(med4);
        listOfMeds.add(med6);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////


        //Place data in Array Adaptor
        MedListAdapter medAdapter = new MedListAdapter(getContext(), R.layout.med_layout, listOfMeds);

        medsLV.setAdapter(medAdapter);
        return view;
    }

    public void getMedications(){
        //check if login information is correct against the database
        final String url = server.url+"getMeds"+"?id="+clientId;
        Log.d("URL", url);

        // prepare the Request
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);

                        String[] json = jsonify(response);
                        Log.d("Response JSON", json[0]);
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

    public String[] jsonify(String response){
        // convert response to array
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(response);
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

        return strArr;
    }


}
