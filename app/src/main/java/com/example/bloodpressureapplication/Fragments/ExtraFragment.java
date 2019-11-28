package com.example.bloodpressureapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bloodpressureapplication.Person;
import com.example.bloodpressureapplication.R;
import com.example.bloodpressureapplication.server;

public class ExtraFragment extends Fragment {
    @Nullable
    EditText email_txt;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View accountView = inflater.inflate(R.layout.fragment_extra, null);

        email_txt = (EditText)accountView.findViewById(R.id.email_T_id);
        email_txt.setText(Person.email);

        //get the name of the person belonging to that email
        //final String url = server.url+"getName"+"?email="+Person.email;
        return accountView;
    }
}
