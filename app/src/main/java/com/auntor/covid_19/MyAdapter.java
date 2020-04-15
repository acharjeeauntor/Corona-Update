package com.auntor.covid_19;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

    ArrayList<Raw_Data> apiData;
    Context context;


    public MyAdapter(ArrayList<Raw_Data> apiData, Context context) {
        this.apiData = apiData;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.row_layout,parent,false);
        MyViewHolder VH = new MyViewHolder(v);
        return VH;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.countryName.setText(apiData.get(position).getCountry());
        holder.activeCase.setText(apiData.get(position).getActive_case());
        holder.todayDeath.setText(apiData.get(position).getNew_death());
        holder.todayInfected.setText(apiData.get(position).getNew_case());
        holder.totalRecovered.setText(apiData.get(position).getTotal_recovered());
        holder.totalDeath.setText(apiData.get(position).getTotal_death());
        holder.totalInfected.setText(apiData.get(position).getTotal_case());
        holder.critical.setText(apiData.get(position).getSerious_critical());

    }
    @Override
    public int getItemCount() {
        return (apiData == null) ? 0 : apiData.size();
    }

    public void filterList(ArrayList<Raw_Data> filteredList) {
        apiData = filteredList;
        notifyDataSetChanged();
    }
}
