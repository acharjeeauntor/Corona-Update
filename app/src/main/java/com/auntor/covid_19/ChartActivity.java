package com.auntor.covid_19;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChartActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    ArrayList<BarEntry> barEntryArrayList;
    ArrayList<BarEntry> infectedBarEntry;
    ArrayList<String> labelNames;
    CovidApi covidApi;
    ArrayList<Raw_Data> countryData;
    BarChart barChart,barChart2;
    BarDataSet barDataSet,barDataSet2;
    BarData barData,barData2;
    XAxis xAxis,xAxis2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        barChart = findViewById(R.id.barId);
        barChart2 = findViewById(R.id.bar2Id);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://corona.pixonlab.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        covidApi = retrofit.create(CovidApi.class);

        getIncomingIntent();
    }




    private void getIncomingIntent(){
        if(getIntent().hasExtra("Country_Name")) {
            Log.d("Contryname", "getIncomingIntent: found intent extras.");
            String country = getIntent().getStringExtra("Country_Name");
setChartData(country);

        }else{
            Toast.makeText(getApplicationContext(),"Country name Not Found",Toast.LENGTH_SHORT).show();
        }
    }

    private void setChartData(String country) {

        setProgressDialog();


        barEntryArrayList = new ArrayList<>();
        labelNames = new ArrayList<>();
        infectedBarEntry = new ArrayList<>();

        //TODO:Start Retrofit
        Call<OurMainDataClass> call = covidApi.getCountryData(country);
    call.enqueue(new Callback<OurMainDataClass>() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onResponse(Call<OurMainDataClass> call, Response<OurMainDataClass> response) {
            if (response.isSuccessful()) {
                countryData = (ArrayList<Raw_Data>) response.body().getData();
                for (int i = 0; i < 7; i++) {
                    //TODO:GET DAte
                    String dates = countryData.get(i).getCreated_at().substring(0,10);

                    //TODO:GET New Death
                    String newDeath = countryData.get(i).getNew_death();
                    String death = newDeath.replace("+", "");
                    String finalFormat = death.replace(",", "");
                    int ND = !finalFormat.equals("") ? Integer.parseInt(finalFormat) : 0;

                    //TODO:GET New Infected
                    String newInfected = countryData.get(i).getNew_case();
                    String infected = newInfected.replace("+", "");
                    String f = infected.replace(",", "");
                    int NI = !f.equals("") ? Integer.parseInt(f) : 0;



                    barEntryArrayList.add(new BarEntry(i, ND));
                    labelNames.add(dates);
                    infectedBarEntry.add(new BarEntry(i,NI));

                   barinfo();
                   barinfo2();
                  progressDialog.dismiss();


                }
            } else {
                // Log.d("Error","Failed");
                Toast.makeText(getApplicationContext(), "Server Error " + "\n" + "Please Wait For Sometimes", Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        public void onFailure(Call<OurMainDataClass> call, Throwable t) {
            Toast.makeText(getApplicationContext(), "Server Error " + "\n" + "Please Wait For Sometimes", Toast.LENGTH_SHORT).show();
        }
    });

    }




    public void barinfo2(){

        barDataSet2 = new BarDataSet(infectedBarEntry,"Last 7 days infected Record");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
        barData2 = new BarData(barDataSet2);
        barChart2.setData(barData2);
        xAxis2 = barChart.getXAxis();
        xAxis2.setValueFormatter(new IndexAxisValueFormatter(labelNames));
        xAxis2.setPosition(XAxis.XAxisPosition.TOP);
        xAxis2.setDrawGridLines(false);
        xAxis2.setDrawAxisLine(false);
        xAxis2.setGranularity(1f);
        xAxis2.setLabelCount(labelNames.size());
        xAxis2.setLabelRotationAngle(270);
        barChart2.animateY(2000);
        barChart2.invalidate();

    }



    public void barinfo(){

        barDataSet = new BarDataSet(barEntryArrayList,"Last 7 days Death Record");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barData = new BarData(barDataSet);
        barChart.setData(barData);
        xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labelNames));
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(labelNames.size());
        xAxis.setLabelRotationAngle(270);
        barChart.animateY(2000);
        barChart.invalidate();

    }


    public void setProgressDialog(){
        //Init Progress Dialog
        progressDialog = new ProgressDialog(ChartActivity.this);
        //show Dialog
        progressDialog.show();
        //Set Content View
        progressDialog.setContentView(R.layout.progress_dialog);
        //Set Transparent background
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.setCancelable(false);
    }
}
