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
import com.plata.carcare.databinding.ActivityAddExpenseBinding;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddExpenseActivity extends AppCompatActivity {

    private ActivityAddExpenseBinding binding;
    private EditText expenseDateET, expenseNameET, expenseDescET, expenseCycleET, expenseCostET;
    private TextView expenseMileageTV;
    private Button saveNotifiBtn;
    private boolean ifOnlyEdition;
    private int expenseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddExpenseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();

        if (getIntent().getStringExtra("ifOnlyEdition").equals("true")) {
            //edit activity
            ifOnlyEdition = true;
            expenseId = Integer.parseInt(getIntent().getStringExtra("id"));
            // get data from DB
            Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM EXPENSE WHERE id = " + String.valueOf(expenseId));
            if (cursor == null)
                return;

            int count = 1;
            while (cursor.moveToNext() && count == 1) {
                String date = cursor.getString(1);
                int mileage = cursor.getInt(2);
                String name = cursor.getString(3);
                double cost = cursor.getDouble(4);
                String desc = cursor.getString(5);

                expenseDateET.setText(date);
                expenseMileageTV.setText(String.valueOf(mileage));
                expenseNameET.setText(name);
                expenseCostET.setText(String.valueOf(cost));
                expenseDescET.setText(desc);
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
                expenseMileageTV.setText(String.valueOf(mileage));
                ++count;
            }
        }

        saveNotifiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //save notify to DB
//                    String date = expenseDateET.getText().toString();
//                    int mileage = Integer.parseInt(expenseMileageTV.getText().toString());
//                    String name = expenseNameET.getText().toString();
//                    double cost = Double.parseDouble(expenseCostET.getText().toString());
//                    String desc = expenseDescET.getText().toString();


                    String serviceName = expenseNameET.getText().toString();
                    int serviceCycle;
                    try {
                        serviceCycle = Integer.parseInt(expenseCycleET.getText().toString());
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Niewłaściwy format specyfikacji cyklu", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    int mileage = Integer.parseInt(expenseMileageTV.getText().toString());
                    String serviceTime = String.valueOf(mileage + serviceCycle);
                    String serviceType = "WYDATEK";

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

    private void init() {
        expenseNameET = (EditText) findViewById(R.id.newExpenseNameET);
        expenseDateET = (EditText) findViewById(R.id.newExpenseDateET);
        expenseDescET = (EditText) findViewById(R.id.newExpenseDescET);
        expenseCycleET = (EditText) findViewById(R.id.newExpenseCycleET);
        expenseMileageTV = (TextView) findViewById(R.id.newExpenseMileageAutofillTV);
        expenseCostET = (EditText) findViewById(R.id.newExpenseCostET) ;
        saveNotifiBtn = (Button) findViewById(R.id.newExpenseSaveNotifiBtn);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_expense, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.expenseSaveBtn) {
            String name = expenseNameET.getText().toString();
            if (name.equals("")) {
                Toast.makeText(getApplicationContext(), "Wpisz nazwę wydatku!", Toast.LENGTH_SHORT).show();
                return false;
            }

            double cost = 0.0;
            try {
                cost = Double.parseDouble(expenseCostET.getText().toString());
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Niewłaściwy format kwoty!", Toast.LENGTH_SHORT).show();
            }

            String date = expenseDateET.getText().toString();
            try {
                Date checkedDate = new SimpleDateFormat("dd-mm-yyyy").parse(date);
            } catch (ParseException e) {
                Toast.makeText(getApplicationContext(), "Niewłaściwy format daty! -> dd-mm-yyyy", Toast.LENGTH_SHORT).show();
                return false;
            }

            int mileage;
            try {
                mileage = Integer.parseInt(expenseMileageTV.getText().toString());
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Niewłaściwy format przebiegu!", Toast.LENGTH_SHORT).show();
                return false;
            }

            String desc = expenseDescET.getText().toString();

            if (ifOnlyEdition) {
                //update expense
                try {
                    MainActivity.sqLiteHelper.updateExpense(
                            expenseId,
                            date,
                            mileage,
                            name,
                            cost,
                            desc
                    );
                    Toast.makeText(getApplicationContext(), "Zaktualizowano poniesiony wydatek", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                //insert expense
                try {
                    MainActivity.sqLiteHelper.insertExpense(
                            date,
                            mileage,
                            name,
                            cost,
                            desc
                    );
                    Toast.makeText(getApplicationContext(), "Dodano nowy poniesiony wydatek", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            finish();  // ukończenie Activity będącej na pierwszym planie
            Intent intent = new Intent(AddExpenseActivity.this, ExpensesActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}