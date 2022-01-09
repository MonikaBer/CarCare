package com.plata.carcare.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.plata.carcare.databinding.ActivityExpensesBinding;

public class ExpensesActivity extends AppCompatActivity {

    private ActivityExpensesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExpensesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}