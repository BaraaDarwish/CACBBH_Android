package com.example.baraa.cabbh;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String username ;
    String email ;
    String first_name ;
    String last_name ;
    private String token;



    public void check_token(final String token2){

        String url = "http://baraadervis.pythonanywhere.com/FS/me/";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try{

                             username = response.getString("username");
                             email = response.getString("email");
                             first_name = response.getString("first_name");
                             last_name = response.getString("last_name");
                            if(username!= null && username.length()>0) {
                                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                                SharedPreferences.Editor editor = pref.edit();

                                editor.putString("username",username);
                                editor.putString("email",email);
                                editor.putString("first_name",first_name);
                                editor.putString("last_name",last_name);

                                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            }


                        }
                        catch (JSONException e){
                            e.printStackTrace();
                            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(intent);

                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        error.printStackTrace();


                    }
                }){ //no semicolon or coma
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization",  "Token "+ token2);
                return params;
            }
        };

        // Access the RequestQueue through your singleton class.
        MySingelton.getInstance(MainActivity.this).addToRequsetQueue(jsonObjectRequest);



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //check if we have a token
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode


        token = pref.getString("token","");
        if(token.equals("") || token.length()<15)
        {
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
        check_token(token);


    }

}
