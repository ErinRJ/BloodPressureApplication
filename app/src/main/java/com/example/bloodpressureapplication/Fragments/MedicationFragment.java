package com.example.bloodpressureapplication.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bloodpressureapplication.R;

import java.util.ArrayList;

public class MedicationFragment extends Fragment {
    ListView medsLV;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Setting values
        View view = inflater.inflate(R.layout.fragment_meds, null);
        medsLV = (ListView) view.findViewById(R.id.medsLV);

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
}
