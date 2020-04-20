package com.auntor.covid_19;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private CovidApi covidApi;
    ArrayList<Raw_Data> apiData;
    Context mcontext;



    public MyAdapter(ArrayList<Raw_Data> apiData, Context context) {
        this.apiData = apiData;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        MyViewHolder VH = new MyViewHolder(v);
        return VH;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://corona.pixonlab.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        covidApi = retrofit.create(CovidApi.class);

        //TODO:Calculate Death Rate
        String x =apiData.get(position).getTotal_death().replace("+", "").replace(",","");
        String y = apiData.get(position).getTotal_case().replace("+","").replace(",","");

        long tD=Integer.parseInt(x);
       long tC=Integer.parseInt(y);

           double c = ((double)tD/tC)*100;
        String result = String.format("%.2f", c);


        holder.countryName.setText(apiData.get(position).getCountry());
        holder.activeCase.setText(apiData.get(position).getActive_case());
        holder.todayDeath.setText(apiData.get(position).getNew_death());
        holder.todayInfected.setText(apiData.get(position).getNew_case());
        holder.totalRecovered.setText(apiData.get(position).getTotal_recovered());
        holder.totalDeath.setText(apiData.get(position).getTotal_death());
        holder.totalInfected.setText(apiData.get(position).getTotal_case());
        holder.critical.setText(apiData.get(position).getSerious_critical());
        holder.lastUpdate.setText(apiData.get(position).getUpdated_at()+"(GMT)");
        holder.deathRate.setText(result+"%");

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, ChartActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                  intent.putExtra("Country_Name",apiData.get(position).getCountry());
                mcontext.startActivity(intent);
            }
        });
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
