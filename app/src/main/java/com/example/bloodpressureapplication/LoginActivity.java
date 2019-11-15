package com.example.bloodpressureapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public EditText email;
    public EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText)findViewById(R.id.email_txt);
        password = (EditText)findViewById(R.id.password_txt);
    }

    public void createAccount(View view) {
        //allow the user to create an account
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        //check that both fields have been filled out
        String em = email.getText().toString();
        String pass = password.getText().toString();

        //if not both filled out, notify the user
        if ((em.equals("")) || (pass.equals(""))){
            Toast.makeText(this, "Please fill in email and password", Toast.LENGTH_LONG).show();
        }
        else{
            //check if login information is correct against the database

            //if incorrect, send toast message that it's wrong
            //Toast.makeText(this, "Username or password incorrect", Toast.LENGTH_LONG).show();

            //if correct start new activity with username bundled
            Intent intent = new Intent(this, MainActivity.class);
            //intent.putExtra("username", username);
            startActivity(intent);
        }

    }
}
