package com.example.baraa.cabbh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import org.json.*;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import java.util.HashMap;
import java.util.Map;


public class Token extends AppCompatActivity {
    String url = "http://baraadervis.pythonanywhere.com/FS/me/";
    TextView result ;



    public void token() {
        Map<String, String> params = new HashMap();
        params.put("Authorization", "Token "+LoginActivity.TOKEN_);
        JSONObject parameters = new JSONObject(params);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, parameters, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            result.setText(response.getString("username"));
                            //result.setText(response.getString("detail"));
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
                        result.setText("unable to get it");

                    }
                }){ //no semicolon or coma
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization",  "Token "+LoginActivity.TOKEN_);
                return params;
            }
        };

        // Access the RequestQueue through your singleton class.
        MySingelton.getInstance(Token.this).addToRequsetQueue(jsonObjectRequest);



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token);
        result = findViewById(R.id.result_token);
        result.setText(LoginActivity.TOKEN_);
        token();



    }
}
