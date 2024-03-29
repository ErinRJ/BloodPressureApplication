package com.example.bloodpressureapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bloodpressureapplication.Fragments.MedicationFragment;

import java.util.HashMap;
import java.util.Map;

public class EditMedicationActivity extends AppCompatActivity {

    public EditText medNameField;
    public EditText timeField;
    public CheckBox sunC, monC, tuesC, wedC, thursC, friC, satC;

    public boolean sun = false;
    public boolean mon = false;
    public boolean tues = false;
    public boolean wed = false;
    public boolean thurs = false;
    public boolean fri = false;
    public boolean sat = false;

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_medication);

        queue = Volley.newRequestQueue(this);

        // initialize our btns and fields
        medNameField = (EditText)findViewById(R.id.nameVal);
        timeField = (EditText)findViewById(R.id.timeVal);
        sunC = (CheckBox)findViewById(R.id.sun);
        monC = (CheckBox)findViewById(R.id.mon);
        tuesC = (CheckBox)findViewById(R.id.tues);
        wedC = (CheckBox)findViewById(R.id.wed);
        thursC = (CheckBox)findViewById(R.id.thurs);
        friC = (CheckBox)findViewById(R.id.fri);
        satC = (CheckBox)findViewById(R.id.sat);

        // get values passed from main activity
        String name =  getIntent().getStringExtra("NAME");
        String time = getIntent().getStringExtra("TIME");

        // populate text fields
        medNameField.setText(name);
        timeField.setText(time);

        sun = getIntent().getBooleanExtra("SUN", false);
        mon = getIntent().getBooleanExtra("MON", false);
        tues = getIntent().getBooleanExtra("TUES", false);
        wed = getIntent().getBooleanExtra("WED", false);
        thurs = getIntent().getBooleanExtra("THURS", false);
        fri = getIntent().getBooleanExtra("FRI", false);
        sat = getIntent().getBooleanExtra("Sat", false);

        // set check boxes
        sunC.setChecked(sun);
        monC.setChecked(mon);
        tuesC.setChecked(tues);
        wedC.setChecked(wed);
        thursC.setChecked(thurs);
        friC.setChecked(fri);
        satC.setChecked(sat);
    }

    public void cancel(View view) {
        Intent intent = new Intent(this, MedicationFragment.class);
        startActivity(intent);
    }

    public void save(View view) {
        String medName = medNameField.getText().toString();
        String timeValue = timeField.getText().toString();

        // request the server to create record and then return to main activity
        createMedicationRecord(medName, timeValue);
    }

    // TODO: this method will request the server to create a medication record in the db
    public void createMedicationRecord(final String name, final String time) {

        // make the request to the server
        // TODO: Change this to our server url
        String url = "http://httpbin.org/post";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Volley Error: ", String.valueOf(error));
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name);
                params.put("time", time);
                params.put("mon", String.valueOf(mon));
                params.put("tues", String.valueOf(tues));
                params.put("wed", String.valueOf(wed));
                params.put("thurs", String.valueOf(thurs));
                params.put("fri", String.valueOf(fri));
                params.put("sat", String.valueOf(sat));

                return params;
            }
        };
        queue.add(postRequest);

        // after the request is sent return to main activity
        Intent intent = new Intent(this, MedicationFragment.class);
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
