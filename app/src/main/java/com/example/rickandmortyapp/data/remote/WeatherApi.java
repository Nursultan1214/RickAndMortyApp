package com.example.rickandmortyapp.data.remote;

import com.example.rickandmortyapp.data.model.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("weather?")
    Call<Weather> getTemp(
            @Query("q") String city,
            @Query("appid") String id,
            @Query("units") String units
    );
}
