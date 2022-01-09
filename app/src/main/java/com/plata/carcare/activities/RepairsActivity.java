package com.plata.carcare.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.plata.carcare.databinding.ActivityRepairsBinding;

public class RepairsActivity extends AppCompatActivity {

    private ActivityRepairsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRepairsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}