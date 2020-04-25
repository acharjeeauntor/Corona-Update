package com.auntor.covid_19.BangladeshCase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.auntor.covid_19.ChartActivity;
import com.auntor.covid_19.CovidApi;
import com.auntor.covid_19.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyAdapterBd extends RecyclerView.Adapter<MyViewHolderBd> {
    private CovidApi covidApi;
    ArrayList<JSONObject> apiData;
    Context mcontext;
   // ArrayList<Raw_DataBD> rawDataBd;



    public MyAdapterBd(ArrayList<JSONObject> apiData, Context context) {
        this.apiData = apiData;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public MyViewHolderBd onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_bd, parent, false);
        MyViewHolderBd VH = new MyViewHolderBd(v);
        return VH;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderBd holder, final int position) {

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://covid19tracker.gov.bd/api/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        covidApi = retrofit.create(CovidApi.class);


        try {
            holder.districtName.setText(apiData.get(position).getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            holder.districtBanglaName.setText(apiData.get(position).getString("bnName"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            holder.confirmedCase.setText(apiData.get(position).getString("confirmed"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mcontext, ChartActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                  intent.putExtra("Country_Name",apiData.get(position).getCountry());
//                mcontext.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return (apiData == null) ? 0 : apiData.size();
    }

    public void filterList(ArrayList<JSONObject> filteredList) {
        apiData = filteredList;
        notifyDataSetChanged();

    }



}
