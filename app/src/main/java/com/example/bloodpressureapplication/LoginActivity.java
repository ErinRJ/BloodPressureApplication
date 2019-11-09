package com.example.bloodpressureapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void createAccount(View view) {
        //allow the user to create an account
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        //check that both fields have been filled out

        //if not both filled out, notify the user
        //Toast.makeText(this, "Please enter both a username and a password", Toast.LENGTH_LONG).show();

        //check if login information is correct against the database

        //if incorrect, send toast message that it's wrong
        //Toast.makeText(this, "Username or password incorrect", Toast.LENGTH_LONG).show();

        //if correct start new activity with username bundled
        Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra("username", username);
        startActivity(intent);
    }
}
