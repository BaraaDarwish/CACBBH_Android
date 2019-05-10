package com.example.baraa.cabbh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.*;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import java.util.HashMap;
import java.util.Map;
public class RegisterActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private EditText C_password;
    private EditText firstname;
    private EditText lastname;
    private EditText email;
    private TextView errors;


    public void Register_(View view) {



        if(!password.getText().toString().equals( C_password.getText().toString()))
            errors.setText("Passwords don't match");

        else if(username.getText().toString().isEmpty())
            errors.setText("username can't be left empty");

        else if(password.getText().toString().isEmpty())
            errors.setText("password can't be left empty");

        else if(C_password.getText().toString() == "")
            errors.setText("password confirmation can't be left empty");

        else if(username.getText().toString().isEmpty())
            errors.setText("username can't be left empty");

        else if(!isValidEmailAddress(email.getText().toString())&& !email.getText().toString().isEmpty())
            errors.setText("username can't be left empty");

        else if(password.getText().toString().length()<8)
            errors.setText("password must be at least 8 characters");
        else {
            String url = "http://baraadervis.pythonanywhere.com/FS/create-user";
            Map<String, String> params = new HashMap();
            params.put("username", username.getText().toString());
            params.put("password", password.getText().toString());
            params.put("first_name", firstname.getText().toString());
            params.put("last_name", lastname.getText().toString());
            params.put("email", email.getText().toString());

            JSONObject parameters = new JSONObject(params);


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {

                                if (!response.getString("username").isEmpty())
                                {

                                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                    intent.putExtra("username",response.getString("username"));
                                    startActivity(intent);
                                }


                            } catch (JSONException e) {
                                errors.setText(e.getMessage());
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            error.printStackTrace();

                        }
                    });

            // Access the RequestQueue through your singleton class.
            MySingelton.getInstance(RegisterActivity.this).addToRequsetQueue(jsonObjectRequest);
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
         username = findViewById(R.id.username);
         password  = findViewById(R.id.password);
         C_password = findViewById(R.id.input_password2);
         firstname = findViewById(R.id.input_first_name);
         lastname = findViewById(R.id.input_last_name);
         email = findViewById(R.id.input_email);
         errors= findViewById(R.id.errors_text);
    }
    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
