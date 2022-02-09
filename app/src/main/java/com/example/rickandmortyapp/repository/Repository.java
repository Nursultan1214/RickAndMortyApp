package com.example.rickandmortyapp.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.rickandmortyapp.common.Resource;
import com.example.rickandmortyapp.data.model.Weather;
import com.example.rickandmortyapp.data.remote.WeatherApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private WeatherApi api;
    private String city;

    public Repository(WeatherApi api) {
        this.api = api;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public MutableLiveData<Resource<Weather>> getTemp() {

        MutableLiveData<Resource<Weather>> data = new MutableLiveData<>();

        data.setValue(Resource.loading());

        api.getTemp(city, "54b50b30b4085c7eb289b8012e9c5a7d", "metric").enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                data.setValue(Resource.success(response.body()));
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                data.setValue(Resource.error(t.getLocalizedMessage(), null));
            }
        });
        return data;
    }

}
