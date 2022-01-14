package com.plata.carcare.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.plata.carcare.R;
import com.plata.carcare.adapters.ControlsAdapter;
import com.plata.carcare.databinding.ActivityControlsBinding;
import com.plata.carcare.model.Control;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ControlsActivity extends AppCompatActivity {

    private ActivityControlsBinding binding;
    public static ArrayList<Control> controlsList;
    RecyclerView controlsRV;
    ControlsAdapter controlsAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityControlsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();

        controlsAdapter = new ControlsAdapter(controlsList, getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        controlsRV.setLayoutManager(mLayoutManager);
        controlsRV.setAdapter(controlsAdapter);

        // get data from DB
        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM CONTROL");
        controlsList.clear();
        if (cursor == null)
            return;

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            Control.Status status = Control.Status.valueOf(cursor.getString(1));
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

            controlsList.add(new Control(id, status, date, name, mileage, desc, type));
        }
    }

    private void init() {
        controlsRV = findViewById(R.id.controlsRV);
        controlsList = new ArrayList<>();
    }

    public void click(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.addControlFab:
                intent = new Intent(ControlsActivity.this, AddControlActivity.class);
                intent.putExtra("ifOnlyEdition", "false");
                break;
        }
        startActivity(intent);
    }
}