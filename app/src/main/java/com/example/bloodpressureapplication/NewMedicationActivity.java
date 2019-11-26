package com.example.bloodpressureapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewMedicationActivity extends AppCompatActivity {

    public EditText medName;
    public EditText timeVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_medication);
    }

    public void cancel(View view) {

    }

    public void save(View view) {
    }

    public void onCheckboxClicked(View view) {
        
    }
}
