package com.plata.carcare.activities;

import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.plata.carcare.adapters.RepairTypesAdapter;
import com.plata.carcare.databinding.ActivityTypeChooseRepairBinding;
import com.plata.carcare.R;
import com.plata.carcare.model.RepairType;
import java.util.ArrayList;

public class TypeChooseRepairActivity extends AppCompatActivity {

    private ActivityTypeChooseRepairBinding binding;
    public static ArrayList<RepairType> repairTypesList;
    RecyclerView repairTypesRV;
    RepairTypesAdapter repairTypesAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTypeChooseRepairBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();

        int repairId = Integer.parseInt(getIntent().getStringExtra("id"));
        boolean ifOnlyEdition = Boolean.parseBoolean(getIntent().getStringExtra("ifOnlyEdition"));

        repairTypesAdapter = new RepairTypesAdapter(repairTypesList, getApplicationContext(), repairId, ifOnlyEdition);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        repairTypesRV.setLayoutManager(mLayoutManager);
        repairTypesRV.setAdapter(repairTypesAdapter);

        // get data from DB
        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM SEASON_CHANGE_TYPE");
        repairTypesList.clear();
        if (cursor == null)
            return;

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String type = cursor.getString(1);

            repairTypesList.add(new RepairType(id, type));
        }
    }

    private void init() {
        repairTypesRV = findViewById(R.id.repairTypesRV);
        repairTypesList = new ArrayList<>();
    }
}