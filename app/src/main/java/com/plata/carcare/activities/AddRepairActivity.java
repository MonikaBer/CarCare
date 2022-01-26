package com.plata.carcare.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.plata.carcare.databinding.ActivityAddRepairBinding;
import com.plata.carcare.R;
import com.plata.carcare.model.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddRepairActivity extends AppCompatActivity {

    private ActivityAddRepairBinding binding;
    private EditText repairNameET, repairDateET, repairDescET, repairCycleET;
    private EditText repairPartsCostET, repairSeasonET;
    private TextView repairTypeTV, repairMileageTV;
    private Button saveNotifiBtn;
    private boolean ifOnlyEdition;
    private int repairId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddRepairBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();

        if (getIntent().getStringExtra("ifOnlyEdition").equals("true")) {
            //edit activity
            ifOnlyEdition = true;
            repairId = Integer.parseInt(getIntent().getStringExtra("id"));
            // get data from DB
            Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM SEASON_CHANGE WHERE id = " + String.valueOf(repairId));
            if (cursor == null)
                return;

            int count = 1;
            while (cursor.moveToNext() && count == 1) {
                String date = cursor.getString(2);
                String name = cursor.getString(3);
                int mileage = cursor.getInt(4);
                String desc = cursor.getString(5);
                String type = cursor.getString(6);
                double partsCost = cursor.getDouble(7);
                String season = cursor.getString(8);

                repairDateET.setText(date);
                repairNameET.setText(name);
                repairMileageTV.setText(String.valueOf(mileage));
                repairDescET.setText(desc);
                repairTypeTV.setText(type);
                repairPartsCostET.setText(String.valueOf(partsCost));
                repairSeasonET.setText(season);
                ++count;
            }
        } else {
            //add activity
            ifOnlyEdition = false;
            // get data from DB
            Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM CAR");
            if (cursor == null)
                return;

            int count = 1;
            while (cursor.moveToNext() && count == 1) {
                int mileage = cursor.getInt(2);
                repairMileageTV.setText(String.valueOf(mileage));
                ++count;
            }
        }

        if (getIntent().getStringExtra("repairType") != null) {
            repairTypeTV.setText(getIntent().getStringExtra("repairType"));
        }

        saveNotifiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //save notify to DB
                    String serviceName = repairTypeTV.getText().toString();
                    String serviceTime = repairCycleET.getText().toString();
                    if (!serviceTime.equals("lato") && !serviceTime.equals("zima")) {
                        Toast.makeText(getApplicationContext(), "Niewłaściwy format specyfikacji cyklu (lato/zima)", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String serviceType = "SEZON";

                    MainActivity.sqLiteHelper.insertNotifi(
                            serviceName,
                            serviceTime,
                            serviceType
                    );

                    Toast.makeText(getApplicationContext(), "Dodano nowe powiadomienie", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_repair, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.repairSaveBtn) {
            String name = repairNameET.getText().toString();
            if (name.equals("")) {
                Toast.makeText(getApplicationContext(), "Wprowadź nazwę wymiany sezonowej!", Toast.LENGTH_SHORT).show();
                return false;
            }

            String date = repairDateET.getText().toString();
            try {
                Date checkedDate = new SimpleDateFormat("dd-mm-yyyy").parse(date);
            } catch (ParseException e) {
                Toast.makeText(getApplicationContext(), "Niewłaściwy format daty! -> dd-mm-yyyy", Toast.LENGTH_SHORT).show();
                return false;
            }

            int mileage;
            try {
                mileage = Integer.parseInt(repairMileageTV.getText().toString());
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Niewłaściwy format przebiegu!", Toast.LENGTH_SHORT).show();
                return false;
            }

            String desc = repairDescET.getText().toString();

            String type;
            try {
                type = repairTypeTV.getText().toString();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Wybierz typ naprawy!", Toast.LENGTH_SHORT).show();
                return false;
            }

            double partsCost;
            try {
                partsCost = Double.parseDouble(repairPartsCostET.getText().toString());
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Niewłaściwy format ceny części!", Toast.LENGTH_SHORT).show();
                return false;
            }

            String season = repairSeasonET.getText().toString();
            if (!season.equals("zima") && !season.equals("lato")) {
                Toast.makeText(getApplicationContext(), "Wprowadź sezon (lato/zima)!", Toast.LENGTH_SHORT).show();
                return false;
            }

            if (ifOnlyEdition) {
                //update repair
                try {
                    MainActivity.sqLiteHelper.updateSeasonChange(
                            repairId,
                            Service.Status.DONE,
                            date,
                            name,
                            mileage,
                            desc,
                            type,
                            partsCost,
                            season
                    );
                    Toast.makeText(getApplicationContext(), "Zaktualizowano dokonaną wymianę sezonową", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                //insert repair
                try {
                    MainActivity.sqLiteHelper.insertSeasonChange(
                            Service.Status.DONE,
                            date,
                            name,
                            mileage,
                            desc,
                            type,
                            partsCost,
                            season
                    );
                    Toast.makeText(getApplicationContext(), "Dodano nową dokonaną wymianę sezonową", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            finish();  // ukończenie Activity będącej na pierwszym planie
            Intent intent = new Intent(AddRepairActivity.this, RepairsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        repairTypeTV = (TextView) findViewById(R.id.newRepairTypeChooseTV);
        repairNameET = (EditText) findViewById(R.id.newRepairNameET);
        repairDateET = (EditText) findViewById(R.id.newRepairDateET);
        repairDescET = (EditText) findViewById(R.id.newRepairDescET);
        repairCycleET = (EditText) findViewById(R.id.newRepairCycleET);
        repairMileageTV = (TextView) findViewById(R.id.newRepairMileageAutofillTV);
        saveNotifiBtn = (Button) findViewById(R.id.newRepairSaveNotifiBtn);
        repairPartsCostET = (EditText) findViewById(R.id.newRepairPartsCostET);
        repairSeasonET = (EditText) findViewById(R.id.newRepairSeasonET);
    }

    public void click(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.newRepairTypeChooseTV:
                intent = new Intent(AddRepairActivity.this, TypeChooseRepairActivity.class);
                intent.putExtra("id", String.valueOf(repairId));
                intent.putExtra("ifOnlyEdition", String.valueOf(ifOnlyEdition));
                break;
        }
        startActivity(intent);
    }
}