package com.example.colorpicker;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Elena
 */
public class ColorActivity extends AppCompatActivity  implements PopupMenu.OnMenuItemClickListener {
    private static final String TAG = "testingTAG";
    SeekBar redSeekBar, greenSeekBar, blueSeekBar;
    int progressRed = 0, progressGreen = 0, progressBlue = 0;
    SeekBar.OnSeekBarChangeListener seekBarListener;
    TextWatcher textWatcher;
    View view;
    ImageButton optionsButton;
    EditText editTextRED, editTextGREEN, editTextBLUE;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_activity);
        //inicializar las vistas
        this.view = findViewById(R.id.colorBkgView);
        this.blueSeekBar = findViewById(R.id.seekBarBLUE);
        this.greenSeekBar = findViewById(R.id.seekBarGREEN);
        this.redSeekBar = findViewById(R.id.seekBarRED);
        this.optionsButton = findViewById(R.id.optionsInput);

        //implementar LISTENERS para los seek bars
        seekBarListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (seekBar.getId()) {
                    case R.id.seekBarRED:
                        progressRed = seekBar.getProgress();
                        editTextRED.setText(String.valueOf(progressRed));
                        view.setBackgroundColor(Color.rgb(progressRed, progressGreen, progressBlue));
                        break;
                    case R.id.seekBarGREEN:
                        progressGreen = seekBar.getProgress();
                        editTextGREEN.setText(String.valueOf(progressGreen));
                        view.setBackgroundColor(Color.rgb(progressRed, progressGreen, progressBlue));
                        break;
                    case R.id.seekBarBLUE:
                        progressBlue = seekBar.getProgress();
                        editTextBLUE.setText(String.valueOf(progressBlue));
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

        this.optionsButton.setOnClickListener(v -> {

            PopupMenu popup = new PopupMenu(this, v);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.app_menu, popup.getMenu());
             popup.setOnMenuItemClickListener(ColorActivity.this);
            popup.show();
        });

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

/*
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
                    if (ETred < 256) {
                        view.setBackgroundColor(Color.rgb(ETred, ETgreen, ETblue));
                        redSeekBar.setProgress(ETred);
                    } else {
                        Toast.makeText(RGBActivity.this, "No se permiten valores mayores de 255", Toast.LENGTH_SHORT).show();
                        s.clear();

                    }
                } catch (NumberFormatException ignored) {

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
                    if (ETgreen < 256) {
                        view.setBackgroundColor(Color.rgb(ETred, ETgreen, ETblue));
                        greenSeekBar.setProgress(ETgreen);
                    } else {
                        Toast.makeText(RGBActivity.this, "No se permiten valores mayores de 255", Toast.LENGTH_SHORT).show();
                        s.clear();
                    }
                } catch (NumberFormatException ignored) {

                }

            }
        });
        editTextBLUE.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextView letterB = findViewById(R.id.letterB);
                letterB.setTextColor(Color.BLUE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    ETblue = Integer.parseInt(s.toString());
                    if (ETblue < 256) {
                        view.setBackgroundColor(Color.rgb(ETred, ETgreen, ETblue));
                        blueSeekBar.setProgress(ETblue);
                        //  letterB.setTextColor(Color.GRAY);
                    } else {
                        Toast.makeText(RGBActivity.this, "No se permiten valores mayores de 255", Toast.LENGTH_SHORT).show();
                        s.clear();
                    }
                } catch (NumberFormatException ignored) {

                }

            }
        });

*/

    }
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.toHex:
                    Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                case R.id.toRGB:
                    Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                    item.setChecked(!item.isChecked());
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }


}
