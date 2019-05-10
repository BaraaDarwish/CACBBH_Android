package com.example.baraa.cabbh;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiabetesResultFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    public static ArrayList<DiabetesResult> data ;
    public  MyDiabetesResultRecyclerViewAdapter adapter;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DiabetesResultFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static DiabetesResultFragment newInstance(int columnCount) {
        DiabetesResultFragment fragment = new DiabetesResultFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diabetesresult_list, container, false);
        data = new ArrayList<>();
        get_diabetes_results();


        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            adapter= new MyDiabetesResultRecyclerViewAdapter(data);
            recyclerView.setAdapter(adapter);

        }
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




    public void get_diabetes_results() {

        String url = "http://baraadervis.pythonanywhere.com/CAC/diabetes-results-api/";



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            //Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();

                            JSONArray array = response.getJSONArray("results");

                            for (int i=0; i<array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);

                                DiabetesResult res = new DiabetesResult();
                               res.setAge(obj.getInt("Age"));
                               res.setName(obj.getString("name"));
                               res.setBloodPressure(obj.getInt("BloodPressure"));
                               res.setResult(obj.getString("result"));
                               res.setBMI((float)obj.getDouble("BMI"));
                               res.setSkinThickness(obj.getInt("SkinThickness"));
                               res.setInsulin(obj.getInt("Insulin"));
                               res.setFunction((float)obj.getDouble("DiabetesPedigreeFunction"));
                               res.setGlucose(obj.getInt("Glucose"));
                               res.setPregnancies(obj.getInt("Pregnancies"));

                               DiabetesResultFragment.data.add(res);

                            }
                            adapter.notifyDataSetChanged();
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
                        Toast.makeText(getContext(), "unable to get results", Toast.LENGTH_SHORT).show();
                    }
                }){ //no semicolon or coma
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                SharedPreferences pref = getContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                String my_token =pref.getString("token","");
                params.put("Authorization",  "Token "+my_token);
                return params;
            }
        };

        // Access the RequestQueue through your singleton class.
        MySingelton.getInstance(getActivity()).addToRequsetQueue(jsonObjectRequest);



    }
}