package com.example.bloodpressureapplication.Fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.bloodpressureapplication.R;

import org.w3c.dom.Text;

import java.util.List;

public class MedListAdapter extends ArrayAdapter<Medication> {
    Context context;
    int resource;

    public MedListAdapter(@NonNull Context context, int resource, @NonNull List<Medication> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        String medName = getItem(position).getMedName();
        String time = getItem(position).getTime();
        String dosage = getItem(position).getDosage();
        String notes = getItem(position).getNotes();
        String day = getItem(position).getDay();
        boolean active = getItem(position).isActive();

        Medication tempMed = new Medication(medName,time,dosage,notes,day,active);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(this.resource,parent,false);

        TextView medNameTV = (TextView) convertView.findViewById(R.id.medName);
        TextView timeTV = (TextView) convertView.findViewById(R.id.timeTV);
        TextView dosageTV = (TextView) convertView.findViewById(R.id.dosageTV);
        TextView notesTV = (TextView) convertView.findViewById(R.id.notesTV);
        TextView activeTV = (TextView) convertView.findViewById(R.id.activeTV);
        TextView dayTV = (TextView) convertView.findViewById(R.id.dayTV);

        medNameTV.setText(medName);
        timeTV.setText(time);
        dosageTV.setText(dosage);
        notesTV.setText(notes);
        dayTV.setText(day);
        activeTV.setText(Boolean.toString(active));

        return convertView;
    }
}
