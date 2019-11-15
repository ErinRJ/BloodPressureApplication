package com.example.bloodpressureapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccountActivity extends AppCompatActivity {

    EditText name_txt;
    EditText email_txt;
    EditText password_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        name_txt = (EditText)findViewById(R.id.name_txt);
        email_txt = (EditText)findViewById(R.id.email_txt);
        password_txt = (EditText)findViewById(R.id.password_txt);
    }

    public void back(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    public void signup(View view) {
        //get the information entered
        String name = name_txt.getText().toString();
        String email = email_txt.getText().toString();
        String password = password_txt.getText().toString();

        //make sure each section is filled out
        if(name.equals("") || email.equals("") || password.equals("")){
            Toast.makeText(this, "Please enter all three fields", Toast.LENGTH_LONG).show();
        }

        else{
            //send new information to the database

            //move to the main activity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
