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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private CovidApi covidApi;
    RecyclerView RV;
    EditText editText;
    ArrayList<Raw_Data> data;
    MyAdapter mAdapter;

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



    }


    private void filter(String text){
        ArrayList<Raw_Data> filteredList = new ArrayList<>();

        for (Raw_Data item : data) {
            if (item.getCountry().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        mAdapter.filterList(filteredList);
    }



    private void getDataByDate() {

     Call<OurMainDataClass> call= covidApi.getAllData("2020-04-15");
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



