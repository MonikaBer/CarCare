package com.plata.carcare.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.plata.carcare.databinding.ActivityControlsBinding;

public class ControlsActivity extends AppCompatActivity {

    private ActivityControlsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityControlsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}