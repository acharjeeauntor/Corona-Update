package com.auntor.covid_19;


import com.auntor.covid_19.BangladeshCase.OurMainDataClassBd;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CovidApi {


    @GET("data")
    Call<OurMainDataClass> getAllData(@Query("date") String date);

    @GET("details")
    Call<OurMainDataClass> getCountryData(@Query("country") String country);

    @GET("district")
    Call<OurMainDataClassBd> getDistrictData(@Query("fbclid") String clientId);

}
