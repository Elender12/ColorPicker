package com.example.colorpicker;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Elena
 */
public class RGBActivity extends AppCompatActivity {
    private static final String TAG = "testingTAG";
    SeekBar redSeekBar, greenSeekBar, blueSeekBar;
    int progressRed = 0, progressGreen = 0, progressBlue = 0, test = 0;
    SeekBar.OnSeekBarChangeListener seekBarListener;
    Color color;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.rgb_activity);
        Log.d(TAG, "onCreate: CTm por que no vas");
        //inicializar las vistas
        blueSeekBar = findViewById(R.id.seekBarBLUE);
        greenSeekBar = findViewById(R.id.seekBarGREEN);
        redSeekBar = findViewById(R.id.seekBarRED);
        //implementar LISTENERS para los seek bars

        seekBarListener = new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (seekBar.getId()) {
                    case R.id.seekBarRED:
                        Log.d(TAG, "onProgressChanged: " + seekBar.getProgress());
                        break;
                    case R.id.seekBarGREEN:
                        //algo
                        break;
                    case R.id.seekBarBLUE:
                        //otra cosa
                        break;
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };
        redSeekBar.setOnSeekBarChangeListener(seekBarListener);
        greenSeekBar.setOnSeekBarChangeListener(seekBarListener);
        blueSeekBar.setOnSeekBarChangeListener(seekBarListener);


    }

    public void test() {
        Log.d(TAG, "onCreate from test: " + redSeekBar.getProgress());
    }

}
