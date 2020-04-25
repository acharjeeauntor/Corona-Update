package com.auntor.covid_19.BangladeshCase;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.auntor.covid_19.CovidApi;
import com.auntor.covid_19.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BangladeshFragment extends Fragment {


    CovidApi covidApi;
    MyAdapterBd adapter;
    RecyclerView RV;
    EditText editText;
    ArrayList<JSONObject> data;
    ProgressDialog progressDialog;
    SwipeRefreshLayout swipeRefreshLayout;
    RequestQueue mQueue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.bangladesh_fragment, container, false);
        getActivity().setTitle("Bangladesh Corona Live Data");



        //TODO:Check is Internet is on or not?
        if (!isNetwork(getContext())){
            Toast.makeText(getContext(), " No Internet Connection"+"\n"+"Please Turn On Internet Connection", Toast.LENGTH_LONG).show();
        }else{


            //TODO:Find Widgets
            RV = rootView.findViewById(R.id.recyclerIdBd);
            editText = rootView.findViewById(R.id.searchIdBd);
            swipeRefreshLayout=rootView.findViewById(R.id.refreshIdBd);



            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    districtWiseGetData();
                    editText.setText("");
                    swipeRefreshLayout.setRefreshing(false);
                }
            });

            mQueue = Volley.newRequestQueue(getContext());


//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl("http://corona.pixonlab.com/api/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//
//
//            covidApi = retrofit.create(CovidApi.class);
            districtWiseGetData();

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






    private void districtWiseGetData() {
  data = new ArrayList<JSONObject>();

        //ProgressDialog Showing
        setProgressDialog();

//        //TODO: set FbClientID
//String clientId="IwAR0kpdwsDWdafIhfUpS8CW1dmXUb03bzLXxyH3UVtjmy8h2U0QPPwHsS5us";
//        //TODO:Start Retrofit
//        Call<OurMainDataClassBd> call = covidApi.getDistrictData(clientId);
////        Log.d("BD","Call Called");
//call.enqueue(new Callback<OurMainDataClassBd>() {
//    @Override
//    public void onResponse(Call<OurMainDataClassBd> call, Response<OurMainDataClassBd> response) {
//        if (response.isSuccessful()) {
//            // Log.d("BD","response success");
//            data = response.body().getFeatures();
//            RV.setLayoutManager(new LinearLayoutManager(getContext()));
//
//            adapter = new MyAdapterBd(data, getContext());
//            RV.setAdapter(adapter);
//
//            progressDialog.dismiss();
//
//        } else {
//            Log.d("BD","response failed");
//            Toast.makeText(getContext(), "Server Error " + "\n" + "Please Wait For Sometimes", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    public void onFailure(Call<OurMainDataClassBd> call, Throwable t) {
//        Toast.makeText(getContext(), "Server Error " + "\n" + "Please Wait For Sometimes", Toast.LENGTH_SHORT).show();
//
//    }
//});


        //TODO:Volley strat from here

        String url = "http://covid19tracker.gov.bd/api/district?fbclid=IwAR0kpdwsDWdafIhfUpS8CW1dmXUb03bzLXxyH3UVtjmy8h2U0QPPwHsS5us";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("features");
                    Log.d("Res","Response Success");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject mainObj = jsonArray.getJSONObject(i);
                        JSONObject dataObj = mainObj.getJSONObject("properties");
                        Log.d("All","data"+dataObj);
                       data.add(i,dataObj);
                    }
                    RV.setLayoutManager(new LinearLayoutManager(getContext()));

            adapter = new MyAdapterBd(data, getContext());
            RV.setAdapter(adapter);

            progressDialog.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error","Response Failed");
                error.printStackTrace();
            }
        });

        mQueue.add(request);
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

    public boolean isNetwork(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }


    public void filter(String text){
        ArrayList<JSONObject> filteredList = new ArrayList<>(700);
        for (JSONObject item:data) {
            Log.d("Item","i"+item);
            try {
                if (item.getString("name").toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(item);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
//        for (int j=0;j<data.size();j++) {
//            //Log.d("Item","i"+);
//            try {
//                if (data.get(j).getString("name").toLowerCase().contains(text.toLowerCase())) {
//                    filteredList.add(item);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
        adapter.filterList(filteredList);
    }

}
