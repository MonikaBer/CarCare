package com.plata.carcare.activities;

import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.plata.carcare.R;
import com.plata.carcare.adapters.NotifisAdapter;
import com.plata.carcare.databinding.ActivityNotifisBinding;
import com.plata.carcare.model.Notifi;
import java.util.ArrayList;

public class NotifisActivity extends AppCompatActivity {

    private ActivityNotifisBinding binding;
    public static ArrayList<Notifi> notifisList;
    RecyclerView notifisRV;
    NotifisAdapter notifisAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotifisBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();

        notifisAdapter = new NotifisAdapter(notifisList, getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        notifisRV.setLayoutManager(mLayoutManager);
        notifisRV.setAdapter(notifisAdapter);

        // get data from DB
        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM NOTIFI");
        notifisList.clear();
        if (cursor == null)
            return;

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String serviceName = cursor.getString(1);
            String serviceTime = cursor.getString(2);
            String serviceType = cursor.getString(3);

            notifisList.add(new Notifi(id, serviceName, serviceTime, serviceType));
        }
    }

    public void init() {
        notifisRV = findViewById(R.id.notifisRV);
        notifisList = new ArrayList<>();
    }
}