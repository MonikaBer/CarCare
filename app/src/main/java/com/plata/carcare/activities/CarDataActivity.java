package com.plata.carcare.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.plata.carcare.R;
import com.plata.carcare.databinding.ActivityCarDataBinding;

import java.io.InputStream;

public class CarDataActivity extends AppCompatActivity {

    private ActivityCarDataBinding binding;
    private ImageView carPhotoDatumIV;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCarDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        carPhotoDatumIV = findViewById(R.id.carPhotoDatumIV);
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
                //TODO: save data

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
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
}
