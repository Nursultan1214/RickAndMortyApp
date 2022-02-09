package com.example.rickandmortyapp.ui.weatherFrag;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmortyapp.common.Resource;
import com.example.rickandmortyapp.data.model.Weather;
import com.example.rickandmortyapp.repository.Repository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WeatherViewModel extends ViewModel {

    private Repository repository;
    public LiveData<Resource<Weather>> liveData;
    private String city;

    @Inject
    public WeatherViewModel(Repository repository) {
        this.repository = repository;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void getTemp() {
        repository.setCity(city);
        liveData = repository.getTemp();
    }
}
