package com.example.bloodpressureapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bloodpressureapplication.Fragments.MedicationFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NewMedicationActivity extends AppCompatActivity {

    public EditText medNameField;
    public EditText timeField;

    public Button saveBtn;
    public Button cancelBtn;

    public boolean sun = false;
    public boolean mon = false;
    public boolean tues = false;
    public boolean wed = false;
    public boolean thurs = false;
    public boolean fri = false;
    public boolean sat = false;

    String time;

    RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_medication);
        medNameField = (EditText)findViewById(R.id.nameVal);
        timeField = (EditText)findViewById(R.id.timeVal);
        saveBtn = (Button) findViewById(R.id.Save);
        cancelBtn = (Button) findViewById(R.id.cancel);
        queue = Volley.newRequestQueue(this);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String medName = medNameField.getText().toString();
                String timeValue = timeField.getText().toString();
                Log.d("Time Raw", timeValue);


                SimpleDateFormat dateFormat = new SimpleDateFormat("hmmaa");
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("hh:mm aa");

                try {
                    Date date = dateFormat.parse(timeValue);

                    time = dateFormat2.format(date);
                    Log.d("Time", time);
                } catch (ParseException e) {
                    Log.e("Time Error", String.valueOf(e));
                }

                // request the server to create record and then return to main activity
                createMedicationRecord(medName, time);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewMedicationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    // TODO: this method will request the server to create a medication record in the db
    public void createMedicationRecord(final String name, final String time) {

        // make the request to the server
        // TODO: Change this to our server url
        String url = server.url + "createMed";
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

                int sunday = sun ? 1 : 0;
                int monday = mon ? 1 : 0;
                int tuesday = tues ? 1 : 0;
                int wednesday = wed ? 1 : 0;
                int thursday = thurs ? 1 : 0;
                int friday = fri ? 1 : 0;
                int saturday = sat ? 1 : 0;

                params.put("medName", name);
                params.put("clientId", "1");
                params.put("medId", "1");

                params.put("time", time);

                params.put("sun", String.valueOf(sunday));
                params.put("mon", String.valueOf(monday));
                params.put("tues", String.valueOf(tuesday));
                params.put("wed", String.valueOf(wednesday));
                params.put("thurs", String.valueOf(thursday));
                params.put("fri", String.valueOf(friday));
                params.put("sat", String.valueOf(saturday));

                return params;
            }
        };
        queue.add(postRequest);

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
