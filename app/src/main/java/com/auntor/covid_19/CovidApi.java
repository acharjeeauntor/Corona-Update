package com.auntor.covid_19;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CovidApi {


    @GET("data")
    Call<OurMainDataClass> getAllData(@Query("date") String date);

}
