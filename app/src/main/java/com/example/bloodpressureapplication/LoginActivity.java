package com.example.bloodpressureapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    public EditText email;
    public EditText password;
    RequestQueue queue;
    public boolean authenticated = false;
    public String em = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        queue = Volley.newRequestQueue(this);

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
        em = email.getText().toString();
        String pass = password.getText().toString();

        //if not both filled out, notify the user
        if ((em.equals("")) || (pass.equals(""))){
            Toast.makeText(this, "Please fill in email and password", Toast.LENGTH_LONG).show();
        }
        else{

            //check if login information is correct against the database
            final String url = server.url+"authenticate"+"?email="+em+"&password="+pass;
            Log.d("URL", url);

            // prepare the Request
            StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            authenticated = Boolean.valueOf(response);
                            Log.d("Authenticated", String.valueOf(authenticated));
                            Log.d("Response", response);
                            //if incorrect, send toast message that it's wrong
                            if(!authenticated) {
                                Toast.makeText(getApplicationContext(), "Username or password incorrect", Toast.LENGTH_LONG).show();
                            } else {
                                nextPage(em);
                            }
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Error.Response", String.valueOf(error));
                        }
                    }
            );

            // add it to the RequestQueue
            queue.add(getRequest);

        }

    }

    public void nextPage(String email){

        //get the information correlating to the email for that user
        final String url = server.url+"getClient"+"?email="+em;
        Log.d("URL:", url);
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response from server:", response);
                        response = response.substring(2);
                        String[] clientValues = response.split(",");
                        Person.clientID = Integer.parseInt(clientValues[0]);
                        Log.d("Client id:", clientValues[0]);
                        Person.name = clientValues[1];
                        Person.email =  clientValues[2];
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", String.valueOf(error));
                    }
                }
        );

        // add it to the RequestQueue
        queue.add(getRequest);

        //if correct start new activity with username bundled
        Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra("username", username);
        startActivity(intent);
    }
}
