package com.example.rickandmortyapp.ui.search;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.rickandmortyapp.R;
import com.example.rickandmortyapp.base.BaseFragment;
import com.example.rickandmortyapp.databinding.FragmentSearchBinding;

public class SearchFragment extends BaseFragment<FragmentSearchBinding> {

    private NavController controller;

    @Override
    protected FragmentSearchBinding bind() {
        return FragmentSearchBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupObservers() {

    }

    @Override
    protected void setupUI() {
        controller = Navigation.findNavController(requireActivity(), R.id.nav_host);
        binding.btnSearch.setOnClickListener(v -> controller.navigate(SearchFragmentDirections
                .actionSearchFragmentToWeatherFragment(
                        binding.etSearch.getText().toString())));
    }
}