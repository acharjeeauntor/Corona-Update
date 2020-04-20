package com.auntor.covid_19;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CovidApi {


    @GET("data")
    Call<OurMainDataClass> getAllData(@Query("date") String date);

    @GET("details")
    Call<OurMainDataClass> getCountryData(@Query("country") String country);

}
