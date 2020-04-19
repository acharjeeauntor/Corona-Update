package com.auntor.covid_19;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DeashboardFragment extends Fragment {
    private CovidApi covidApi;
    MyAdapter adapter;
    RecyclerView RV;
    EditText editText;
    ArrayList<Raw_Data> data;
    ProgressDialog progressDialog;
    SwipeRefreshLayout swipeRefreshLayout;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.deashboard_fragment, container, false);
        getActivity().setTitle("Corona Live Data");




        //TODO:Check is Internet is on or not?
        if (!isNetwork(getContext())){
            Toast.makeText(getContext(), " No Internet Connection"+"\n"+"Please Turn On Internet Connection", Toast.LENGTH_LONG).show();
        }else{


            //TODO:Find Widgets
            RV = rootView.findViewById(R.id.recyclerId);
            editText = rootView.findViewById(R.id.searchId);
            swipeRefreshLayout=rootView.findViewById(R.id.refreshId);



            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    getDataByDate();
                    editText.setText("");
                    swipeRefreshLayout.setRefreshing(false);
                }
            });


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









        return rootView;
    }

    private void filter(String text) {
//        Log.d("Text",text);
//        Log.d("Data","dd"+data);
        ArrayList<Raw_Data> filteredList = new ArrayList<>();

        for (Raw_Data item : data) {
            if (item.getCountry().toLowerCase().contains(text.toLowerCase())) {
                //Log.d("Item",item.getCountry().toLowerCase());
                filteredList.add(item);
            }
        }
        //Log.d("FilterList","ff"+filteredList);
        adapter.filterList(filteredList);
//Log.d("FilterList","ff"+filteredList);
//        MyAdapter myAdapter = new MyAdapter(filteredList,getApplicationContext()) ;
//   myAdapter.filterList(filteredList);

    }


    private void getDataByDate() {

        //ProgressDialog Showing
        setProgressDialog();

        //TODO:Set Current Date
        Date today = Calendar.getInstance().getTime();//getting date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//formating according to my need
        //Log.i("Date", "" + formatter);
        String da = formatter.format(today);
        // Log.d("Date", da);

        //TODO:Start Retrofit
        Call<OurMainDataClass> call = covidApi.getAllData(da);
        call.enqueue(new Callback<OurMainDataClass>() {
            @Override
            public void onResponse(Call<OurMainDataClass> call, Response<OurMainDataClass> response) {
                if (response.isSuccessful()) {
                    //Log.d("Res","Done");
                    data = (ArrayList<Raw_Data>) response.body().getData();
                    RV.setLayoutManager(new LinearLayoutManager(getContext()));

                    adapter = new MyAdapter(data, getContext());
                    RV.setAdapter(adapter);

                    progressDialog.dismiss();

                } else {
                    // Log.d("Error","Failed");
                    Toast.makeText(getContext(), "Server Error " + "\n" + "Please Wait For Sometimes", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OurMainDataClass> call, Throwable t) {
//Log.d("Error","Failed");
                Toast.makeText(getContext(), "Server Error " + "\n" + "Please Wait For Sometimes", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void setProgressDialog(){
        //Init Progress Dialog
        progressDialog = new ProgressDialog(getContext());
        //show Dialog
        progressDialog.show();
        //Set Content View
        progressDialog.setContentView(R.layout.progress_dialog);
        //Set Transparent background
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progressDialog.setCancelable(false);
    }

//    @Override
//    public void onBackPressed() {
//        progressDialog.setCancelable(false);
//        super.onBackPressed();
//    }

    public boolean isNetwork(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }



}
