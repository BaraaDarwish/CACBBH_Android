package com.example.baraa.cabbh;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyFSResultRecyclerViewAdapter extends RecyclerView.Adapter<MyFSResultRecyclerViewAdapter.ViewHolder> {

    private final List<FSResult> mValues;

    public MyFSResultRecyclerViewAdapter(List<FSResult> items) {
        mValues = items;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_fsresult, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.Old_accuracy.setText(String.valueOf(mValues.get(position).getOld_accuracy()));
        holder.New_accuracy.setText(String.valueOf(mValues.get(position).getNew_accurcay()));
        holder.Old_features.setText(String.valueOf(mValues.get(position).getOld_features()));
        holder.New_features.setText(String.valueOf(mValues.get(position).getNew_features()));
        holder.Name.setText(mValues.get(position).getName());



    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        private final TextView Old_accuracy;
        private final TextView New_accuracy;
        private final TextView Old_features;
        private final TextView New_features;
        private final TextView Name;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            Name = view.findViewById(R.id.f_name);
            Old_accuracy = view.findViewById(R.id.f_old_acc);
            New_accuracy = view.findViewById(R.id.f_new_acc);
            Old_features = view.findViewById(R.id.f_old_feat);
            New_features = view.findViewById(R.id.f_new_features);

        }


    }
}
