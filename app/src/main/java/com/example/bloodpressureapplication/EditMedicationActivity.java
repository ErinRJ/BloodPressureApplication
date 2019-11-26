package com.example.bloodpressureapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class EditMedicationActivity extends AppCompatActivity {

    public EditText medNameField;
    public EditText timeField;

    public boolean sun = false;
    public boolean mon = false;
    public boolean tues = false;
    public boolean wed = false;
    public boolean thurs = false;
    public boolean fri = false;
    public boolean sat = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_medication);
        medNameField = (EditText)findViewById(R.id.nameVal);
        timeField = (EditText)findViewById(R.id.timeVal);


    }

    public void cancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void save(View view) {
        String medName = medNameField.getText().toString();
        String timeValue = timeField.getText().toString();

        // request the server to create record and then return to main activity
        createMedicationRecord(medName, timeValue);
    }

    // TODO: this method will request the server to create a medication record in the db
    public void createMedicationRecord(String name, String time) {

        // make the request to the server

        // after the request is sent return to main activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.sun:
                if (checked) {
                    sun = true;
                } else {
                    sun = false;
                }
                break;
            case R.id.mon:
                if (checked) {
                    mon = true;
                } else {
                    mon = false;
                }
                break;
            case R.id.tues:
                if (checked) {
                    tues = true;
                } else {
                    tues = false;
                }
                break;
            case R.id.wed:
                if (checked) {
                    wed = true;
                } else {
                    wed = false;
                }
                break;
            case R.id.thurs:
                if (checked) {
                    thurs = true;
                } else {
                    thurs = false;
                }
                break;
            case R.id.fri:
                if (checked) {
                    fri = true;
                } else {
                    fri = false;
                }
                break;
            case R.id.sat:
                if (checked) {
                    sat = true;
                } else {
                    sat = false;
                }
                break;
        }
    }
}
