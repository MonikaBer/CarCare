package com.plata.carcare.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.plata.carcare.R;
import com.plata.carcare.databinding.ActivityAddControlBinding;
import com.plata.carcare.model.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddControlActivity extends AppCompatActivity {

    private ActivityAddControlBinding binding;
    private EditText controlNameET, controlDateET, controlDescET, controlCycleET;
    private TextView controlTypeTV, controlMileageTV;
    private Button saveNotifiBtn;
    private boolean ifOnlyEdition;
    private int controlId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddControlBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();

        if (getIntent().getStringExtra("ifOnlyEdition").equals("true")) {
            //edit activity
            ifOnlyEdition = true;
            controlId = Integer.parseInt(getIntent().getStringExtra("id"));
            // get data from DB
            Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM CONTROL WHERE id = " + String.valueOf(controlId));
            if (cursor == null)
                return;

            int count = 1;
            while (cursor.moveToNext() && count == 1) {
                String date = cursor.getString(2);
                String name = cursor.getString(3);
                int mileage = cursor.getInt(4);
                String desc = cursor.getString(5);
                String type = cursor.getString(6);

                controlDateET.setText(date);
                controlNameET.setText(name);
                controlMileageTV.setText(String.valueOf(mileage));
                controlDescET.setText(desc);
                controlTypeTV.setText(type);
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
                controlMileageTV.setText(String.valueOf(mileage));
                ++count;
            }
        }

        if (getIntent().getStringExtra("controlType") != null) {
            controlTypeTV.setText(getIntent().getStringExtra("controlType"));
        }

        saveNotifiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //save notify to DB
                    String serviceName = controlTypeTV.getText().toString();
                    int serviceCycle;
                    try {
                        serviceCycle = Integer.parseInt(controlCycleET.getText().toString());
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Niewłaściwy format specyfikacji cyklu", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    int mileage = Integer.parseInt(controlMileageTV.getText().toString());
                    String serviceTime = String.valueOf(mileage + serviceCycle);
                    String serviceType = "KONTROLA";

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
        getMenuInflater().inflate(R.menu.menu_add_control, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.controlSaveBtn) {
            String date = controlDateET.getText().toString();
            try {
                Date checkedDate = new SimpleDateFormat("dd-mm-yyyy").parse(date);
            } catch (ParseException e) {
                Toast.makeText(getApplicationContext(), "Niewłaściwy format daty! -> dd-mm-yyyy", Toast.LENGTH_SHORT).show();
                return false;
            }

            int mileage;
            try {
                mileage = Integer.parseInt(controlMileageTV.getText().toString());
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Niewłaściwy format przebiegu!", Toast.LENGTH_SHORT).show();
                return false;
            }

            String name = controlNameET.getText().toString();
            String desc = controlDescET.getText().toString();

            String type;
            try {
                type = controlTypeTV.getText().toString();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Wybierz typ kontroli!", Toast.LENGTH_SHORT).show();
                return false;
            }

            if (ifOnlyEdition) {
                //update control
                try {
                    MainActivity.sqLiteHelper.updateControl(
                            controlId,
                            Service.Status.DONE,
                            date,
                            name,
                            mileage,
                            desc,
                            type
                    );
                    Toast.makeText(getApplicationContext(), "Zaktualizowano zrobioną kontrolę", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                //insert control
                try {
                    MainActivity.sqLiteHelper.insertControl(
                            Service.Status.DONE,
                            date,
                            name,
                            mileage,
                            desc,
                            type
                    );
                    Toast.makeText(getApplicationContext(), "Dodano nową zrobioną kontrolę", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            finish();  // ukończenie Activity będącej na pierwszym planie
            Intent intent = new Intent(AddControlActivity.this, ControlsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        controlTypeTV = (TextView) findViewById(R.id.newControlTypeChooseTV);
        controlNameET = (EditText) findViewById(R.id.newControlNameET);
        controlDateET = (EditText) findViewById(R.id.newControlDateET);
        controlDescET = (EditText) findViewById(R.id.newControlDescET);
        controlCycleET = (EditText) findViewById(R.id.newControlCycleET);
        controlMileageTV = (TextView) findViewById(R.id.newControlMileageAutofillTV);
        saveNotifiBtn = (Button) findViewById(R.id.newControlSaveNotifiBtn);
    }

    public void click(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.newControlTypeChooseTV:
                intent = new Intent(AddControlActivity.this, TypeChooseActivity.class);
                intent.putExtra("id", String.valueOf(controlId));
                intent.putExtra("ifOnlyEdition", String.valueOf(ifOnlyEdition));
                break;
        }
        startActivity(intent);
    }
}