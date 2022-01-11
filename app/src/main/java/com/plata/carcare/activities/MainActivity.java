package com.plata.carcare.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.plata.carcare.R;
import com.plata.carcare.SQLiteHelper;
import com.plata.carcare.databinding.ActivityMainBinding;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ImageView carPhotoIV;
    private TextView carNameTV;
    private EditText carMileageET;
    private int id;
    private ImageButton mileagePhotoBtn;
    private Bitmap bitmap;
    public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();

        sqLiteHelper = new SQLiteHelper(this, "CarDB.sqlite", null, 1);
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS CAR (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, mileage INTEGER, photo BLOB, brand TEXT, model TEXT, production_year INTEGER, engine_type TEXT)");
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS CONTROL (id INTEGER PRIMARY KEY AUTOINCREMENT, status TEXT, date TEXT, name TEXT, mileage INTEGER, desc TEXT, type TEXT)");
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS CONTROL_TYPE (id INTEGER PRIMARY KEY AUTOINCREMENT, type TEXT)");
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS SEASON_CHANGE_TYPE (id INTEGER PRIMARY KEY AUTOINCREMENT, type TEXT)");
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS MILEAGE_CHANGE_TYPE (id INTEGER PRIMARY KEY AUTOINCREMENT, type TEXT)");


//        //-----temporary-----
//        sqLiteHelper.insertControlType("poziom oleju");
//        sqLiteHelper.insertControlType("ciśnienie w oponach");
//
//        sqLiteHelper.insertSeasonChangeType("wymiana opon");
//        sqLiteHelper.insertSeasonChangeType("wymiana płynu do spryskiwaczy");
//
//        sqLiteHelper.insertMileageChangeType("wymiana rozrządu");
//        sqLiteHelper.insertMileageChangeType("wymiana oleju silnikowego");
//        //-----temporary-----


        // get data from DB
        Cursor cursor = sqLiteHelper.getData("SELECT * FROM CAR");
        if (cursor == null)
            return;

        int count = 1;
        while (cursor.moveToNext() && count == 1) {
            id = cursor.getInt(0);

            carNameTV.setText(cursor.getString(1));
            carMileageET.setText(String.valueOf(cursor.getInt(2)));

            byte[] photo = cursor.getBlob(3);
            carPhotoIV.setImageBitmap(BitmapFactory.decodeByteArray(photo, 0, photo.length));
            ++count;
        }

        mileagePhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                systemCameraActivityResultLauncher.launch(intent);
            }
        });
    }

    private void init() {
        carPhotoIV = findViewById(R.id.carPhotoIV);
        carNameTV = findViewById(R.id.carNameTV);
        carMileageET = findViewById(R.id.mileageET);
        mileagePhotoBtn = findViewById(R.id.mileagePhotoBtn);
    }

    ActivityResultLauncher<Intent> systemCameraActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK)
                        bitmap = (Bitmap) result.getData().getExtras().get("data");
                }
            });

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
            case R.id.carMileageSaveBtn:
                try {
                    int mileage = Integer.parseInt(carMileageET.getText().toString());
                    sqLiteHelper.updateMileage(id, mileage);
                    Toast.makeText(getApplicationContext(), "Zaktualizowano przebieg", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            case R.id.mileagePhotoBtn:

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

    private void clearDB() {
        sqLiteHelper.queryData("DROP TABLE CAR");
        sqLiteHelper.queryData("DROP TABLE CONTROL");
        sqLiteHelper.queryData("DROP TABLE CONTROL_TYPE");
        sqLiteHelper.queryData("DROP TABLE SEASON_CHANGE_TYPE");
        sqLiteHelper.queryData("DROP TABLE MILEAGE_CHANGE_TYPE");
    }
}