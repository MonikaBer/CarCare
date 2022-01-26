package com.plata.carcare.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.plata.carcare.R;
import com.plata.carcare.adapters.RepairsAdapter;
import com.plata.carcare.databinding.ActivityRepairsBinding;
import com.plata.carcare.model.Repair;
import com.plata.carcare.model.SeasonChange;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RepairsActivity extends AppCompatActivity {

    private ActivityRepairsBinding binding;
    public static ArrayList<Repair> repairsList;
    RecyclerView repairsRV;
    RepairsAdapter repairsAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRepairsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();

        repairsAdapter = new RepairsAdapter(repairsList, getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        repairsRV.setLayoutManager(mLayoutManager);
        repairsRV.setAdapter(repairsAdapter);

        // get data from DB
        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM SEASON_CHANGE");
        repairsList.clear();
        if (cursor == null)
            return;

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            Repair.Status status =Repair.Status.valueOf(cursor.getString(1));
            Date date = null;
            try {
                date = new SimpleDateFormat("dd-mm-yyyy").parse(cursor.getString(2));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String name = cursor.getString(3);
            int mileage = cursor.getInt(4);
            String desc = cursor.getString(5);
            String type = cursor.getString(6);
            double partsCost = cursor.getDouble(7);
            String season = cursor.getString(8);

            repairsList.add(new SeasonChange(id, status, date, name, mileage, desc, type, partsCost, season));
        }
    }

    private void init() {
        repairsRV = findViewById(R.id.repairsRV);
        repairsList = new ArrayList<>();
    }

    public void click(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.addRepairFab:
                intent = new Intent(RepairsActivity.this, AddRepairActivity.class);
                intent.putExtra("ifOnlyEdition", "false");
                break;
        }
        startActivity(intent);
    }
}