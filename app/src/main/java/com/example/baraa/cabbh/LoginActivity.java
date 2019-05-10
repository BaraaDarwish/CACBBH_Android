package com.example.baraa.cabbh;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.*;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    String url = "http://baraadervis.pythonanywhere.com/FS/token/";
    TextView result ;
    TextView username_txt;
    TextView password_txt;
    static String TOKEN_ = "";



    public void Login_(View view) {
        Map<String, String> params = new HashMap();
        params.put("username", username_txt.getText().toString());
        params.put("password", password_txt.getText().toString());

        JSONObject parameters = new JSONObject(params);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                        String TOKEN = response.getString("token");
                        if(TOKEN!=null){
                            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                            SharedPreferences.Editor editor = pref.edit();

                            LoginActivity.TOKEN_ = TOKEN;
                            editor.putString("token",TOKEN);
                            editor.commit();
                            //go to homepage
                            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(intent);
                        }
                        else{
                            result.setText("Login failed"+TOKEN_);
                        }
                    }
                    catch (JSONException e){
                            e.printStackTrace();
                    }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        error.printStackTrace();
                        result.setText("Invalid credentials");

                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingelton.getInstance(LoginActivity.this).addToRequsetQueue(jsonObjectRequest);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        result = findViewById(R.id.errors_text);
        username_txt = findViewById(R.id.username);
        password_txt = findViewById(R.id.password);
        Intent intent = getIntent();
        if(intent.getStringExtra("username")!=null)
            username_txt.setText(intent.getStringExtra("username"));



    }

    public void go_to_token(View view ){

        Intent intent = new Intent(this , Token.class);
        startActivity(intent);

    }
    public void go_to_register(View view){
        Intent intent = new Intent(this , RegisterActivity.class);
        startActivity(intent);
    }
}
