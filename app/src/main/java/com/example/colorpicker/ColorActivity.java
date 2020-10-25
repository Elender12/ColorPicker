package com.example.colorpicker;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
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
    SeekBar redSeekBar, greenSeekBar, blueSeekBar, alphaSeekBar;
    int progressRed = 0, progressGreen = 0, progressBlue = 0;
    SeekBar.OnSeekBarChangeListener seekBarListener;
    TextWatcher textWatcher;
    View view;
    ImageButton optionsButton;
    TextView demoText;
    EditText editTextRED, editTextGREEN, editTextBLUE;
    EditText inputValues;

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
        this.alphaSeekBar= findViewById(R.id.seekBarALPHA);
        this.optionsButton = findViewById(R.id.optionsInput);
        this.demoText= findViewById(R.id.demoText);
        this.inputValues= findViewById(R.id.inputValues);
        //implementar LISTENERS para los seek bars
        seekBarListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(seekBar.getId() == R.id.seekBarRED) {
                        progressRed = seekBar.getProgress();
                        //editTextRED.setText(String.valueOf(progressRed));
                        demoText.setTextColor(Color.rgb(progressRed, progressGreen, progressBlue));
                        view.setBackgroundColor(Color.rgb(progressRed, progressGreen, progressBlue));
                }else if(seekBar.getId() == R.id.seekBarGREEN){
                        progressGreen = seekBar.getProgress();
                        demoText.setTextColor(Color.rgb(progressRed, progressGreen, progressBlue));
                        //editTextGREEN.setText(String.valueOf(progressGreen));
                        view.setBackgroundColor(Color.rgb(progressRed, progressGreen, progressBlue));

                }else if(seekBar.getId() == R.id.seekBarBLUE){
                        progressBlue = seekBar.getProgress();
                        demoText.setTextColor(Color.rgb(progressRed, progressGreen, progressBlue));
                        //editTextBLUE.setText(String.valueOf(progressBlue));
                        view.setBackgroundColor(Color.rgb(progressRed, progressGreen, progressBlue));
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
            this.inputValues.setEnabled(false);

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
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                             /*   ETred = Integer.parseInt(s.toString());
                                ETgreen = Integer.parseInt(s.toString());
                                ETblue = Integer.parseInt(s.toString());
                                view.setBackgroundColor(Color.rgb(ETred, ETgreen, ETblue)); */
                    Log.d(TAG, "afterTextChanged: "+s.toString());

                }catch(NumberFormatException nfe){
                    Log.d(TAG, "afterTextChanged: error");
                }


            }
        };

    }
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int maxLength;
            this.inputValues.setEnabled(true);
            InputFilter[] filters = new InputFilter[1];
                if(item.getItemId() == R.id.toHex){
                  //  Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                    maxLength = 7;
                    this.alphaSeekBar.setEnabled(true);
                    filters[0] = new InputFilter.LengthFilter(maxLength);
                    this.inputValues.setFilters(filters);
                    this.inputValues.setInputType(InputType.TYPE_CLASS_TEXT);
                    this.inputValues.setText("#");
                    Toast.makeText(this, "Selected Item: " + inputValues.toString(), Toast.LENGTH_SHORT).show();
                    this.inputValues.addTextChangedListener(textWatcher);
                    return true;
                }else if(item.getItemId() == R.id.toRGB){
                    maxLength = 6;
                    this.alphaSeekBar.setEnabled(false);
                    Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                    this.inputValues.setInputType(InputType.TYPE_CLASS_NUMBER);
                    filters[0] = new InputFilter.LengthFilter(maxLength);
                    this.inputValues.setFilters(filters);
                    this.inputValues.setText("");
                    this.inputValues.addTextChangedListener(textWatcher);
                    item.setChecked(!item.isChecked());
                    return true;
                }
                    return super.onOptionsItemSelected(item);

        }


}
