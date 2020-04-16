package com.auntor.covid_19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Adapter;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private CovidApi covidApi;
    MyAdapter myAdapter;
    RecyclerView RV;
    EditText editText;
    ArrayList<Raw_Data> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    RV = findViewById(R.id.recyclerId);
    editText = findViewById(R.id.searchId);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://corona.pixonlab.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        covidApi = retrofit.create(CovidApi.class);
        getDataByDate();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
filter(s.toString());
            }
        });

        Log.d("Data","dd"+data);

    }


    private void filter(String text){
        Log.d("Text",text);
        Log.d("Data","dd"+data);
        ArrayList<Raw_Data> filteredList = new ArrayList<>();

        for (Raw_Data item : data) {
            if (item.getCountry().toLowerCase().contains(text.toLowerCase())) {
                Log.d("Item",item.getCountry().toLowerCase());
                filteredList.add(item);
            }
        }
        Log.d("FilterList","ff"+filteredList);
        myAdapter.filterList(filteredList);
//Log.d("FilterList","ff"+filteredList);
//        MyAdapter myAdapter = new MyAdapter(filteredList,getApplicationContext()) ;
//   myAdapter.filterList(filteredList);

    }


    private void getDataByDate() {

        //Set Current Date
        Date today = Calendar.getInstance().getTime();//getting date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//formating according to my need
        String da = formatter.format(today);

        //Start Retrofit
     Call<OurMainDataClass> call= covidApi.getAllData(da);
     call.enqueue(new Callback<OurMainDataClass>() {
         @Override
         public void onResponse(Call<OurMainDataClass> call, Response<OurMainDataClass> response) {
             if(response.isSuccessful()){
                 Log.d("Res","Done");

                data = (ArrayList<Raw_Data>) response.body().getData();
                 RV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                 MyAdapter adapter = new MyAdapter(data,getApplicationContext());
                 RV.setAdapter(adapter);

             }else{
                 Log.d("Error","Failed");
             }
         }

         @Override
         public void onFailure(Call<OurMainDataClass> call, Throwable t) {
Log.d("Error","Failed");
         }
     });

    }


}



