package com.auntor.covid_19.BangladeshCase;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.auntor.covid_19.R;

public class MyViewHolderBd extends RecyclerView.ViewHolder {


    TextView confirmedCase,districtName,districtBanglaName;
    CardView cardView;


    public MyViewHolderBd(@NonNull View itemView) {
        super(itemView);

       confirmedCase = itemView.findViewById(R.id.confirmedCount);
        districtBanglaName = itemView.findViewById(R.id.districtNameBnId);
        districtName = itemView.findViewById(R.id.districtNameId);
        cardView = itemView.findViewById(R.id.cardBdId);

    }

}
