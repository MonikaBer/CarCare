package com.plata.carcare.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.plata.carcare.R;
import com.plata.carcare.databinding.ActivityCarDataBinding;
import com.plata.carcare.utils.Utils;
import java.io.InputStream;

public class CarDataActivity extends AppCompatActivity {

    private ActivityCarDataBinding binding;
    private ImageView carPhotoDatumIV;
    private EditText carNameET, carBrandET, carModelET, carProductionYearET, carEngineTypeET;
    private Bitmap bitmap;
    final int REQUEST_CODE_GALLERY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCarDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();

        // get data from DB
        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM CAR");
        if (cursor == null)
            return;

        int count = 1;
        while (cursor.moveToNext() && count == 1) {
            byte[] photo = cursor.getBlob(3);
            carPhotoDatumIV.setImageBitmap(BitmapFactory.decodeByteArray(photo, 0, photo.length));

            carNameET.setText(cursor.getString(1));
            carBrandET.setText(cursor.getString(4));
            carModelET.setText(cursor.getString(5));
            carProductionYearET.setText(String.valueOf(cursor.getInt(6)));
            carEngineTypeET.setText(cursor.getString(7));
            ++count;
        }
    }

    private void init() {
        carPhotoDatumIV = findViewById(R.id.carPhotoDatumIV);
        carNameET = findViewById(R.id.carNameDatumET);
        carBrandET = findViewById(R.id.carBrandDatumET);
        carModelET = findViewById(R.id.carModelDatumET);
        carProductionYearET = findViewById(R.id.carProductionYearDatumET);
        carEngineTypeET = findViewById(R.id.carEngineTypeDatumET);
    }

    public void click(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.carPhotoDatumIV:
                intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                systemGalleryActivityResultLauncher.launch(intent);
                break;
            case R.id.carDataSaveBtn:
                String name = carNameET.getText().toString();
                byte[] photo = Utils.imageViewToByte(carPhotoDatumIV);
                String brand = carBrandET.getText().toString();
                String model = carModelET.getText().toString();

                int productionYear;
                try {
                    productionYear = Integer.parseInt(carProductionYearET.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Niewłaściwy format roku produkcji!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String engineType = carEngineTypeET.getText().toString();

                Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM CAR");
                if (cursor.moveToNext()) {
                    try {
                        int id = cursor.getInt(0);
                        MainActivity.sqLiteHelper.updateCar(
                                id,
                                name,
                                photo,
                                brand,
                                model,
                                productionYear,
                                engineType
                        );
                        Toast.makeText(getApplicationContext(), "Zaktualizowano dane samochodu", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    MainActivity.sqLiteHelper.insertCar(
                            name,
                            photo,
                            brand,
                            model,
                            productionYear,
                            engineType
                    );
                    Toast.makeText(getApplicationContext(), "Dodano nowy samochód", Toast.LENGTH_SHORT).show();
                }

//                ActivityCompat.requestPermissions(
//                        CarDataActivity.this,
//                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                        REQUEST_CODE_GALLERY
//                );

                //finish();  // ukończenie Activity będącej na pierwszym planie
                intent = new Intent(CarDataActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    ActivityResultLauncher<Intent> systemGalleryActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        InputStream inputStream = null;
                        try {
                            if (bitmap != null)
                                bitmap.recycle();
                            inputStream = getContentResolver().openInputStream(result.getData().getData());
                            bitmap = BitmapFactory.decodeStream(inputStream);
                            carPhotoDatumIV.setImageBitmap(bitmap);
                            carPhotoDatumIV.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 360, 360, false));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
}
