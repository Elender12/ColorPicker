package com.example.colorpicker;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
    int progressRed = 0, progressGreen = 0, progressBlue = 0;
    int ETred = 0, ETgreen = 0, ETblue = 0;
    SeekBar.OnSeekBarChangeListener seekBarListener;
    TextWatcher textWatcher;
    View view;
    EditText editTextRED, editTextGREEN, editTextBLUE;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rgb_activity);
        //inicializar las vistas
        view = findViewById(R.id.colorBkgView);
        blueSeekBar = findViewById(R.id.seekBarBLUE);
        greenSeekBar = findViewById(R.id.seekBarGREEN);
        redSeekBar = findViewById(R.id.seekBarRED);
        editTextRED = findViewById(R.id.editTextRED);
        editTextGREEN = findViewById(R.id.editTextGreen);
        editTextBLUE = findViewById(R.id.editTextBLUE);

        //implementar LISTENERS para los seek bars
        seekBarListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (seekBar.getId()) {
                    case R.id.seekBarRED:
                        progressRed = seekBar.getProgress();
                        view.setBackgroundColor(Color.rgb(progressRed, progressGreen, progressBlue));
                        Log.d(TAG, "onProgressChanged: " + seekBar.getProgress());
                        break;
                    case R.id.seekBarGREEN:
                        progressGreen = seekBar.getProgress();
                        view.setBackgroundColor(Color.rgb(progressRed, progressGreen, progressBlue));
                        break;
                    case R.id.seekBarBLUE:
                        progressBlue = seekBar.getProgress();
                        view.setBackgroundColor(Color.rgb(progressRed, progressGreen, progressBlue));
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
     /*   textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    ETred = Integer.parseInt(s.toString());
                    ETgreen = Integer.parseInt(s.toString());
                    ETblue = Integer.parseInt(s.toString());
                    view.setBackgroundColor(Color.rgb(ETred, ETgreen, ETblue));
                }catch(NumberFormatException nfe){
                    Log.d(TAG, "afterTextChanged: error");
                }


            }
        };
            editTextRED.addTextChangedListener(textWatcher);
            editTextGREEN.addTextChangedListener(textWatcher);
            editTextBLUE.addTextChangedListener(textWatcher);*/

        editTextRED.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    ETred = Integer.parseInt(s.toString());
                    view.setBackgroundColor(Color.rgb(ETred, ETgreen, ETblue));

                } catch (NumberFormatException nfe) {

                }

            }
        });
        editTextGREEN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    ETgreen = Integer.parseInt(s.toString());
                    view.setBackgroundColor(Color.rgb(ETred, ETgreen, ETblue));
                } catch (NumberFormatException nfe) {

                }

            }
        });
        editTextBLUE.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    ETblue = Integer.parseInt(s.toString());
                    view.setBackgroundColor(Color.rgb(ETred, ETgreen, ETblue));
                } catch (NumberFormatException nfe) {

                }

            }
        });

    }
}
