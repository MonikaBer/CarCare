package com.plata.carcare.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.plata.carcare.R;
import com.plata.carcare.databinding.ActivityMainBinding;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void click(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.carDataBtn:
                intent = new Intent(MainActivity.this, CarDataActivity.class);
                break;
            case R.id.notifisBtn:
                intent = new Intent(MainActivity.this, NotifisActivity.class);
                break;
            case R.id.controlsBtn:
                intent = new Intent(MainActivity.this, ControlsActivity.class);
                break;
            case R.id.repairsBtn:
                intent = new Intent(MainActivity.this, RepairsActivity.class);
                break;
            case R.id.expensesBtn:
                intent = new Intent(MainActivity.this, ExpensesActivity.class);
                break;
        }
        startActivity(intent);
    }
}