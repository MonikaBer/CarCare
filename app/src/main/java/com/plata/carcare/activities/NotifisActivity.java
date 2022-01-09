package com.plata.carcare.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.plata.carcare.databinding.ActivityNotifisBinding;

public class NotifisActivity extends AppCompatActivity {

    private ActivityNotifisBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotifisBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}