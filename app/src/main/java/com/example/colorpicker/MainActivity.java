package com.example.colorpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menu = findViewById(R.id.btnMenu);
        menu.setOnClickListener(v -> {
            Intent intent= new Intent(this, ColorActivity.class);
            startActivity(intent);
         /*
            PopupMenu popup = new PopupMenu(this, v);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.app_menu, popup.getMenu());
             popup.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) MainActivity.this);
            popup.show()*/
            ;

         //   popup.setOnMenuItemClickListener(MainActivity.this);

           // popup.show();
        });


    }

/*    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.toHex:
                Intent intent = new Intent(this, HexActivity.class);
                startActivity(intent);
                return true;
            case R.id.toRGB:
                intent = new Intent(this, RGBActivity.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }*/
}