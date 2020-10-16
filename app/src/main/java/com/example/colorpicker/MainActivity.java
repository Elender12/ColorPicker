package com.example.colorpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button hexBtn, rgbBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hexBtn = findViewById(R.id.hexBtn);
        rgbBtn = findViewById(R.id.rgbBtn);
        hexBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, HexActivity.class);
            startActivity(intent);
        });
        rgbBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, RGBActivity.class);
            startActivity(intent);
        });


    }


}