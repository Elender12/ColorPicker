package com.example.colorpicker;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Elena
 */
public class ColorActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private static final String TAG = "testingTAG";
    SeekBar redSeekBar, greenSeekBar, blueSeekBar;
    int progressRed = 0, progressGreen = 0, progressBlue = 0, colorHex = 0;
    TextView r, g, b, hash, demoText, lblRed, lblGreen, lblBlue;
    SeekBar.OnSeekBarChangeListener seekBarListener;
    TextWatcher textWatcher;
    View view;
    ImageButton optionsButton;
    EditText editTextRED, editTextGREEN, editTextBLUE, inputValues;
    CustomTextWatcher ctwR, ctwG, ctwB;
    String hex = "";

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
        this.demoText = findViewById(R.id.demoText);
        this.inputValues = findViewById(R.id.inputValues);
        this.editTextRED = findViewById(R.id.editTextRED);
        this.editTextGREEN = findViewById(R.id.editTextGREEN);
        this.editTextBLUE = findViewById(R.id.editTextBLUE);
        this.r = findViewById(R.id.tvR);
        this.g = findViewById(R.id.tvG);
        this.b = findViewById(R.id.tvB);
        this.hash = findViewById(R.id.tvHash);
        this.view.setBackgroundColor(Color.WHITE);
        this.lblRed= findViewById(R.id.lblRed);
        this.lblGreen = findViewById(R.id.lblGreen);
        this.lblBlue = findViewById(R.id.lblBlue);
        //disable inputs
        this.inputValues.setEnabled(false);
        disableEditTextRGB();
        disableSeekbars();
        //options menu
        this.optionsButton.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(this, v);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.app_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(ColorActivity.this);
            popup.show();
        });

        //implementar LISTENERS para los seek bars
        seekBarListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (seekBar.getId() == R.id.seekBarRED) {
                    progressRed = seekBar.getProgress();
                    editTextRED.setText(String.valueOf(progressRed));
                    demoText.setTextColor(Color.rgb(progressRed, progressGreen, progressBlue));
                    view.setBackgroundColor(Color.rgb(progressRed, progressGreen, progressBlue));
                    colorHex = Color.argb(0, progressRed, progressGreen, progressBlue);
                    hex = Integer.toHexString(colorHex);
                    if (!inputValues.isEnabled()) {
                        inputValues.setText(hex);
                    }
                } else if (seekBar.getId() == R.id.seekBarGREEN) {
                    progressGreen = seekBar.getProgress();
                    demoText.setTextColor(Color.rgb(progressRed, progressGreen, progressBlue));
                    editTextGREEN.setText(String.valueOf(progressGreen));
                    view.setBackgroundColor(Color.rgb(progressRed, progressGreen, progressBlue));
                    colorHex = Color.argb(0, progressRed, progressGreen, progressBlue);
                    hex = Integer.toHexString(colorHex);
                    if (!inputValues.isEnabled()) {
                        inputValues.setText(hex);
                    }
                }else if(seekBar.getId() == R.id.seekBarBLUE) {
                    progressBlue = seekBar.getProgress();
                    demoText.setTextColor(Color.rgb(progressRed, progressGreen, progressBlue));
                    editTextBLUE.setText(String.valueOf(progressBlue));
                    view.setBackgroundColor(Color.rgb(progressRed, progressGreen, progressBlue));
                    colorHex = Color.argb(0, progressRed, progressGreen, progressBlue);
                    hex = Integer.toHexString(colorHex);
                    if (!inputValues.isEnabled()) {
                        inputValues.setText(hex);
                    }
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        };

        //text watchers
        ctwR = new CustomTextWatcher(editTextRED, redSeekBar, "red", this, this.inputValues);
        ctwG = new CustomTextWatcher(editTextGREEN, greenSeekBar, "green", this, this.inputValues);
        ctwB = new CustomTextWatcher(editTextBLUE, blueSeekBar, "blue", this, this.inputValues);
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               inputValues.setSelection(inputValues.length());
            }
            @Override
            public void afterTextChanged(Editable s) {
                int color;
                int r, g, b;
                inputValues.setSelection(inputValues.length());
                int hexFormat = 7;
                String HEX_PATTERN = "^#([A-Fa-f0-9]{6})$";
                Pattern p = Pattern.compile(HEX_PATTERN);
                Matcher m = p.matcher(s.toString());
                boolean isValid = m.matches();
                if (s.length()== hexFormat && isValid) {
                    try {
                        color = Color.parseColor(String.valueOf(s));
                        r = Color.red(color);
                        g = Color.green(color);
                        b = Color.blue(color);
                        editTextRED.setText(String.valueOf(r));
                        redSeekBar.setProgress(r);
                        editTextGREEN.setText(String.valueOf(g));
                        greenSeekBar.setProgress(g);
                        editTextBLUE.setText(String.valueOf(b));
                        blueSeekBar.setProgress(b);
                        view.setBackgroundColor(Color.parseColor(s.toString()));
                        demoText.setTextColor(Color.parseColor(s.toString()));
                    } catch(IllegalArgumentException iae){
                        Toast.makeText(ColorActivity.this, "El color no está en un formato correcto.", Toast.LENGTH_LONG).show();
                        resetDemoColors();
                        s.clear();
                    };
                } else if(s.length() == hexFormat && !isValid){
                    Toast.makeText(ColorActivity.this, "El color no está en un formato correcto.", Toast.LENGTH_LONG).show();
                    s.clear();
                    clearEditText();
                    resetBarProgress();
                    resetDemoColors();
                }
            }
        };
    }
        @Override
        public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.toHex) {
                    disableTextViewRGB();
                    disableEditTextRGB();
                    resetBarProgress();
                    disableSeekbars();
                    resetDemoColors();
                    clearEditText();
                    disableRGBLabels();
                    this.inputValues.setEnabled(true);
                    this.hash.setTextColor(Color.BLACK);
                    this.inputValues.getText().clear();
                    this.inputValues.addTextChangedListener(textWatcher);
                    item.setChecked(!item.isChecked());
                    return true;
                }else if(item.getItemId() == R.id.toRGB) {
                    resetDemoColors();
                    disableSeekbars();
                    disableRGBLabels();
                    enableEditTextRGB();
                    activeTextViewRGB();
                    resetBarProgress();
                    this.hash.setTextColor(Color.GRAY);
                    this.inputValues.setEnabled(false);
                    this.inputValues.getText().clear();
                    clearEditText();
                    this.editTextRED.addTextChangedListener(ctwR);
                    this.editTextGREEN.addTextChangedListener(ctwG);
                    this.editTextBLUE.addTextChangedListener(ctwB);
                    item.setChecked(!item.isChecked());
                    return true;
                } else if (item.getItemId() == R.id.Bars) {
                    disableTextViewRGB();
                    disableEditTextRGB();
                    enableSeekBars();
                    resetBarProgress();
                    clearEditText();
                    enableRGBLabels();
                    this.hash.setTextColor(Color.GRAY);
                    this.inputValues.setEnabled(false);
                    this.inputValues.getText().clear();
                    redSeekBar.setOnSeekBarChangeListener(seekBarListener);
                    greenSeekBar.setOnSeekBarChangeListener(seekBarListener);
                    blueSeekBar.setOnSeekBarChangeListener(seekBarListener);
                    item.setChecked(!item.isChecked());
                    return true;
                }
            return super.onOptionsItemSelected(item);
        }

    //methods
    public void activeTextViewRGB() {
        this.r.setTextColor(Color.RED);
        this.g.setTextColor(Color.GREEN);
        this.b.setTextColor(Color.BLUE);
    }

    public void disableTextViewRGB() {
        this.r.setTextColor(Color.GRAY);
        this.g.setTextColor(Color.GRAY);
        this.b.setTextColor(Color.GRAY);
    }

    public void enableEditTextRGB() {
        this.editTextBLUE.setEnabled(true);
        this.editTextGREEN.setEnabled(true);
        this.editTextRED.setEnabled(true);
    }

    public void disableEditTextRGB() {
        this.editTextBLUE.setEnabled(false);
        this.editTextGREEN.setEnabled(false);
        this.editTextRED.setEnabled(false);
    }

    public void enableSeekBars() {
        this.redSeekBar.setEnabled(true);
        this.greenSeekBar.setEnabled(true);
        this.blueSeekBar.setEnabled(true);
    }

    public void resetBarProgress() {
        this.redSeekBar.setProgress(0);
        this.greenSeekBar.setProgress(0);
        this.blueSeekBar.setProgress(0);
    }

    public void disableSeekbars() {
        this.redSeekBar.setEnabled(false);
        this.greenSeekBar.setEnabled(false);
        this.blueSeekBar.setEnabled(false);
    }

    public void clearEditText() {
        this.editTextBLUE.getText().clear();
        this.editTextGREEN.getText().clear();
        this.editTextRED.getText().clear();
    }

    public void resetDemoColors() {
        this.view.setBackgroundColor(Color.WHITE);
        this.demoText.setTextColor(Color.BLACK);
    }
    public void enableRGBLabels(){
        this.lblRed.setTextColor(Color.RED);
        this.lblGreen.setTextColor(Color.GREEN);
        this.lblBlue.setTextColor(Color.BLUE);
    }
    public void disableRGBLabels(){
        this.lblRed.setTextColor(Color.GRAY);
        this.lblGreen.setTextColor(Color.GRAY);
        this.lblBlue.setTextColor(Color.GRAY);
    }
}
