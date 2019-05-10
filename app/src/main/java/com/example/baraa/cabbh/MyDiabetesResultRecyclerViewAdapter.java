package com.example.baraa.cabbh;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

public class MyDiabetesResultRecyclerViewAdapter extends RecyclerView.Adapter<MyDiabetesResultRecyclerViewAdapter.ViewHolder> {

    private final List<DiabetesResult> mValues;


    public MyDiabetesResultRecyclerViewAdapter(List<DiabetesResult> items) {
        mValues = items;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_diabetesresult, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.Pregnancies.setText(String.valueOf(mValues.get(position).getPregnancies()));
        holder.Glucose.setText(String.valueOf(mValues.get(position).getGlucose()));
        holder.BloodPressure.setText(String.valueOf(mValues.get(position).getBloodPressure()));
        holder.SkinThickness.setText(String.valueOf(mValues.get(position).getSkinThickness()));

        holder.Insulin.setText(String.valueOf(mValues.get(position).getInsulin()));
        holder.BMI.setText(String.valueOf(mValues.get(position).getBMI()));
        holder.DiabetesPedigreeFunction.setText(String.valueOf(mValues.get(position).getFunction()));
        holder.Age.setText(String.valueOf(mValues.get(position).getAge()));
        holder.result.setText(mValues.get(position).getResult());


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        private final TextView Pregnancies;
        private final TextView Glucose;
        private final TextView BloodPressure;
        private final TextView SkinThickness;
        private final TextView Insulin;
        private final TextView BMI;
        private final TextView DiabetesPedigreeFunction;
        private final TextView Age;
        private final TextView result;


        public ViewHolder(View view) {
            super(view);
            mView = view;

            Pregnancies = view.findViewById(R.id.d_preg);
            BloodPressure = view.findViewById(R.id.d_blood);
            Glucose= view.findViewById(R.id.d_glu);
            SkinThickness= view.findViewById(R.id.d_skin);
            Insulin= view.findViewById(R.id.d_insulin);
            BMI = view.findViewById(R.id.d_BMI);
            DiabetesPedigreeFunction= view.findViewById(R.id.d_func);
            Age= view.findViewById(R.id.d_age);
            result= view.findViewById(R.id.d_result);

        }


    }
}
