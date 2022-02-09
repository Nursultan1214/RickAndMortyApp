package com.example.rickandmortyapp.ui.weatherFrag;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.rickandmortyapp.R;
import com.example.rickandmortyapp.base.BaseFragment;
import com.example.rickandmortyapp.data.model.Weather;
import com.example.rickandmortyapp.databinding.FragmentWeatherBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WeatherFragment extends BaseFragment<FragmentWeatherBinding> {

    private WeatherFragmentArgs args;
    private WeatherViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            args = WeatherFragmentArgs.fromBundle(getArguments());
        }
    }

    @Override
    protected FragmentWeatherBinding bind() {
        return FragmentWeatherBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupObservers() {
        viewModel.liveData.observe(getViewLifecycleOwner(), response -> {
            switch (response.status) {
                case SUCCESS:
                    setData(response.data);
                    binding.progress.setVisibility(View.GONE);
                    break;
                case LOADING:
                    binding.progress.setVisibility(View.VISIBLE);
                    break;
                case ERROR:
                    Toast.makeText(requireActivity(), "Error not inet", Toast.LENGTH_SHORT).show();
                    binding.progress.setVisibility(View.GONE);
                    break;
            }
        });
    }

    @Override
    protected void setupUI() {
        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);
        viewModel.setCity(args.getCity());
        viewModel.getTemp();
        NavController controller = Navigation.findNavController(requireActivity(), R.id.nav_host);
        binding.tvLocation.setOnClickListener(v
                -> controller.navigate(R.id.action_weatherFragment_to_searchFragment));
    }

    private void setData(Weather weather) {
        String location = weather.getName();
        String urlImg = "https://openweathermap.org/img/wn/" + weather.getWeather().get(0).getIcon() + ".png";
        String maxTemp = Math.round(weather.getMain().getTempMax()) + "C";
        String wind = String.valueOf((int) Math.round(weather.getWind().getSpeed()));
        String minTemp = String.valueOf((int) Math.round(weather.getMain().getTempMin()));
        String humidity = String.valueOf(weather.getMain().getHumidity());
        String barometr = String.valueOf(weather.getMain().getPressure());
        String mainWeather = weather.getWeather().get(0).getMain();
        String tempNow = String.valueOf((int) Math.round(weather.getMain().getTemp()));

        binding.tvPressure.setText(barometr);
        binding.tvLocation.setText(location);
        binding.tvSky.setText(tempNow);
        Glide.with(requireActivity()).load(urlImg).into(binding.ivCloud);
        binding.tvMaxtemperature.setText(maxTemp);
        binding.tvWind.setText(wind);
        binding.tvMintemperature.setText(minTemp);
        binding.percent.setText(humidity);
        binding.tvGradusy.setText(mainWeather);
    }
}