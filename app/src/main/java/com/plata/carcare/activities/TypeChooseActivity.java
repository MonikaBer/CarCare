package com.plata.carcare.activities;

import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.plata.carcare.adapters.ControlTypesAdapter;
import com.plata.carcare.databinding.ActivityTypeChooseBinding;
import com.plata.carcare.R;
import com.plata.carcare.model.ControlType;
import java.util.ArrayList;

public class TypeChooseActivity extends AppCompatActivity {

    private ActivityTypeChooseBinding binding;
    public static ArrayList<ControlType> controlTypesList;
    RecyclerView controlTypesRV;
    ControlTypesAdapter controlTypesAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTypeChooseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();

        int controlId = Integer.parseInt(getIntent().getStringExtra("id"));
        boolean ifOnlyEdition = Boolean.parseBoolean(getIntent().getStringExtra("ifOnlyEdition"));

        controlTypesAdapter = new ControlTypesAdapter(controlTypesList, getApplicationContext(), controlId, ifOnlyEdition);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        controlTypesRV.setLayoutManager(mLayoutManager);
        controlTypesRV.setAdapter(controlTypesAdapter);

        // get data from DB
        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM CONTROL_TYPE");
        controlTypesList.clear();
        if (cursor == null)
            return;

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String type = cursor.getString(1);

            controlTypesList.add(new ControlType(id, type));
        }
    }

    private void init() {
        controlTypesRV = findViewById(R.id.controlTypesRV);
        controlTypesList = new ArrayList<>();
    }
}