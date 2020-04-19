package com.auntor.covid_19;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {


    TextView totalInfected, totalDeath, totalRecovered, critical,
            todayInfected, todayDeath, lastUpdate, activeCase, countryName, deathRate;
    CardView cardView;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        countryName = itemView.findViewById(R.id.countryNameId);
        totalInfected = itemView.findViewById(R.id.tvinfectedcount);
        activeCase = itemView.findViewById(R.id.tvActivecount);
        todayInfected = itemView.findViewById(R.id.todayinfectedcount);
        todayDeath = itemView.findViewById(R.id.todaydeathcount);
        totalDeath = itemView.findViewById(R.id.totaldeathcount);
        lastUpdate = itemView.findViewById(R.id.lastUpdateCount);
        totalRecovered = itemView.findViewById(R.id.tvreccount);
        critical = itemView.findViewById(R.id.tvCriticalcount);
        cardView = itemView.findViewById(R.id.cardId);
        deathRate = itemView.findViewById(R.id.deathRateCount);
    }

}
