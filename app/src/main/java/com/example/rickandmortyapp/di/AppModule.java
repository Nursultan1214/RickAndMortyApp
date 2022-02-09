package com.example.rickandmortyapp.di;

import com.example.rickandmortyapp.data.remote.RetrofitClient;
import com.example.rickandmortyapp.data.remote.WeatherApi;
import com.example.rickandmortyapp.repository.Repository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {

    @Provides
    public static WeatherApi provideApi() {
        return new RetrofitClient().provideApi();
    }

    @Provides
    public static Repository provideMainRepository(WeatherApi api) {
        return new Repository(api);
    }
}
