package com.plata.carcare.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.plata.carcare.R;
import com.plata.carcare.adapters.ExpensesAdapter;
import com.plata.carcare.databinding.ActivityExpensesBinding;
import com.plata.carcare.model.Expense;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ExpensesActivity extends AppCompatActivity {

    private ActivityExpensesBinding binding;
    public static ArrayList<Expense> expensesList;
    RecyclerView expensesRV;
    ExpensesAdapter expensesAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExpensesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();

        expensesAdapter = new ExpensesAdapter(expensesList, getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        expensesRV.setLayoutManager(mLayoutManager);
        expensesRV.setAdapter(expensesAdapter);

        // get data from DB
        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM EXPENSE");
        expensesList.clear();
        if (cursor == null)
            return;

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            Date date = null;
            try {
                date = new SimpleDateFormat("dd-mm-yyyy").parse(cursor.getString(1));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int mileage = cursor.getInt(2);
            String name = cursor.getString(3);
            double cost = cursor.getDouble(4);
            String desc = cursor.getString(5);

            expensesList.add(new Expense(id, date, mileage, name, cost, desc));
        }
    }

    private void init() {
        expensesRV = findViewById(R.id.expensesRV);
        expensesList = new ArrayList<>();
    }

    public void click(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.addExpenseFab:
                intent = new Intent(ExpensesActivity.this, AddExpenseActivity.class);
                intent.putExtra("ifOnlyEdition", "false");
                break;
        }
        startActivity(intent);
    }
}
