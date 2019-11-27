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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class CreateAccountActivity extends AppCompatActivity {

    EditText name_txt;
    EditText email_txt;
    EditText password_txt;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        name_txt = (EditText)findViewById(R.id.name_txt);
        email_txt = (EditText)findViewById(R.id.email_txt);
        password_txt = (EditText)findViewById(R.id.password_txt);
        queue = Volley.newRequestQueue(this);
    }

    public void back(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    public void signup(View view) {
        //get the information entered
        final String name = name_txt.getText().toString();
        final String email = email_txt.getText().toString();
        final String password = password_txt.getText().toString();

        //make sure each section is filled out
        if(name.equals("") || email.equals("") || password.equals("")){
            Toast.makeText(this, "Please enter all three fields", Toast.LENGTH_LONG).show();
        }

        else{
            //send new information to the database
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
                    params.put("email", email);
                    params.put("password", password);

                    return params;
                }
            };
            queue.add(postRequest);
            //move to the main activity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
