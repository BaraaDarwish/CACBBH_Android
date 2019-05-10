package com.example.baraa.cabbh;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
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


public class SettingsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    public String mParam1;
    public String mParam2;
    public TextView username;
    public TextView firstname;
    public TextView lastname;
    public TextView email;





    public SettingsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        username= view.findViewById(R.id.st_username);
        firstname= view.findViewById(R.id.st_firstname);
        lastname= view.findViewById(R.id.st_lastname);
        email = view.findViewById(R.id.st_email);
        SharedPreferences pref = getContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        String user =pref.getString("username","");


        if(user.equals(""))
            check_token();
        else
            setTexts();
        Button btn = view.findViewById(R.id.btn_logout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();

                editor.putString("token","");
                editor.putString("username","");
                editor.commit();
                //go to homepage
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });



        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }



    @Override
    public void onDetach() {
        super.onDetach();

    }



    public void check_token(){

        String url = "http://baraadervis.pythonanywhere.com/FS/me/";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            String username_ = response.getString("username");
                            String email_ = response.getString("email");
                            String firstname_ = response.getString("first_name");
                            String lastname_ = response.getString("last_name");
                            if(username!= null) {
                                SharedPreferences pref = getActivity().getSharedPreferences("MyPref", 0); // 0 - for private mode
                                SharedPreferences.Editor editor = pref.edit();

                                editor.putString("username",username_);
                                editor.putString("email",email_);
                                editor.putString("firstname",firstname_);
                                editor.putString("lastname",lastname_);
                                editor.commit();
                                setTexts();


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


                    }
                }){ //no semicolon or coma
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                SharedPreferences pref = getContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                String token=pref.getString("token","");
                params.put("Authorization",  "Token "+ token);
                return params;
            }
        };

        // Access the RequestQueue through your singleton class.
        MySingelton.getInstance(getActivity()).addToRequsetQueue(jsonObjectRequest);



    }
    private void setTexts()
    {

        SharedPreferences pref = getContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        String user =pref.getString("username","");
        String first =pref.getString("firstname","");
        String last =pref.getString("lastname","");
        String Email =pref.getString("email","");
        username.setText("Username :"+user);
        firstname.setText("First name :"+first);
        lastname.setText("Last name :"+last);
        email.setText("Email :"+Email);
    }
}
