package com.example.bloodpressureapplication.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.bloodpressureapplication.Person;
import com.example.bloodpressureapplication.R;
import com.example.bloodpressureapplication.server;

import java.util.HashMap;
import java.util.Map;

public class ExtraFragment extends Fragment {
    @Nullable
    EditText email_txt;
    EditText name_txt;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View accountView = inflater.inflate(R.layout.fragment_extra, null);

        email_txt = accountView.findViewById(R.id.email_T_id);
        name_txt = accountView.findViewById(R.id.name_T_id);
        email_txt.setText(Person.email);
        name_txt.setText(Person.name);

        return accountView;
    }
}
