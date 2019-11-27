package com.example.bloodpressureapplication.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.bloodpressureapplication.MainActivity;
import com.example.bloodpressureapplication.R;
import com.example.bloodpressureapplication.server;

import java.util.HashMap;
import java.util.Map;

public class PressureEntryFragment extends Fragment {

    EditText sysField;
    EditText diaField;
    Button submit;
    RequestQueue queue;

    String sys;
    String dia;
    String timestamp;
    // TODO: need to get id at login so we can use it here
    String clientId;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View bpView = inflater.inflate(R.layout.fragment_bp, null);

        sysField = (EditText) bpView.findViewById(R.id.sys_txt);
        diaField = (EditText) bpView.findViewById(R.id.dia_txt);
        submit = (Button) bpView.findViewById(R.id.bp_submit_btn);

        queue = Volley.newRequestQueue(getActivity().getApplicationContext());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sys = String.valueOf(sysField.getText());
                dia = String.valueOf(diaField.getText());
                Long tsLong = System.currentTimeMillis()/1000;
                timestamp = tsLong.toString();
                clientId = String.valueOf(1);

                // make the request to create a new BP entry for the user
                String url = server.url+"createBP";
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                // response
                                Log.d("BP", response);
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
                        params.put("clientId", clientId);
                        params.put("systolic", sys);
                        params.put("diastolic", dia);
                        params.put("timestamp", timestamp);

                        return params;
                    }
                };
                queue.add(postRequest);
                sysField.setText("");
                diaField.setText("");
            }
        });

        return bpView;
    }

//    public void submit(View view) {
//        sys = String.valueOf(sysField.getText());
//        dia = String.valueOf(diaField.getText());
//        Long tsLong = System.currentTimeMillis()/1000;
//        String ts = tsLong.toString();
//        clientId = 1;
//
//        submitBP(sys, dia, clientId, ts);
//
//    }

//    public void submitBP(final String sys, final String dia, final int id, final String timestamp){
//        // make the request to create a new BP entry for the user
//        String url = server.url+"createBP";
//        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>()
//                {
//                    @Override
//                    public void onResponse(String response) {
//                        // response
//                        Log.d("Response", response);
//                    }
//                },
//                new Response.ErrorListener()
//                {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // error
//                        Log.d("Volley Error: ", String.valueOf(error));
//                    }
//                }
//        ) {
//            @Override
//            protected Map<String, String> getParams()
//            {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("clientId", String.valueOf(id));
//                params.put("systolic", sys);
//                params.put("diastolic", dia);
//                params.put("timestamp", timestamp);
//
//                return params;
//            }
//        };
//        queue.add(postRequest);
//
//    }
}
