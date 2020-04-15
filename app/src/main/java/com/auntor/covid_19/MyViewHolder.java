package com.auntor.covid_19;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder{

    TextView totalInfected,totalDeath,totalRecovered,critical,
            todayInfected,todayDeath,deathRate,activeCase,countryName;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        countryName = itemView.findViewById(R.id.countryNameId);
        totalInfected = itemView.findViewById(R.id.tvinfectedcount);
        activeCase = itemView.findViewById(R.id.tvActivecount);
        todayInfected = itemView.findViewById(R.id.todayinfectedcount);
        todayDeath = itemView.findViewById(R.id.todaydeathcount);
        totalDeath = itemView.findViewById(R.id.totaldeathcount);
        deathRate = itemView.findViewById(R.id.deathratecount);
        totalRecovered = itemView.findViewById(R.id.tvreccount);
        critical = itemView.findViewById(R.id.tvCriticalcount);
    }
}
