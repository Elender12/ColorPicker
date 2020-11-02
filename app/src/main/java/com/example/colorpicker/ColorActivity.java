package com.example.colorpicker;

import android.graphics.Color;
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
public class ColorActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private static final String TAG = "testingTAG";
    SeekBar redSeekBar, greenSeekBar, blueSeekBar;
    int progressRed = 0, progressGreen = 0, progressBlue = 0;
    int colorHex = 0;
    TextView r, g, b, hash;
    SeekBar.OnSeekBarChangeListener seekBarListener;
    TextWatcher textWatcher;
    View view;
    ImageButton optionsButton;
    TextView demoText;
    EditText editTextRED, editTextGREEN, editTextBLUE;
    EditText inputValues;
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
        //disable inputs
        this.inputValues.setEnabled(false);
        disableEditTextRGB();
        disableSeekbars();
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
                    inputValues.setText(hex);
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
                    inputValues.setText(hex);
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
                    inputValues.setText(hex);
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
        redSeekBar.setOnSeekBarChangeListener(seekBarListener);
        greenSeekBar.setOnSeekBarChangeListener(seekBarListener);
        blueSeekBar.setOnSeekBarChangeListener(seekBarListener);
        //options menu
        this.optionsButton.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(this, v);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.app_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(ColorActivity.this);
            popup.show();
        });

        //text watchers
        ctwR = new CustomTextWatcher(editTextRED, redSeekBar, view, "red", this.inputValues, this.demoText);
        ctwG = new CustomTextWatcher(editTextGREEN, greenSeekBar, view, "green", this.inputValues, this.demoText);
        ctwB = new CustomTextWatcher(editTextBLUE, blueSeekBar, view, "blue", this.inputValues, this.demoText);
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int hexFormat = 7;
                String value = s.toString();

                if (s.length() == hexFormat) {

                    try {
                        String startCharcter= "";
                        startCharcter =  value.substring(0,1);

                        Log.d(TAG, "afterTextChanged: Entra en la condición con "+s);
                        Log.d(TAG, "afterTextChanged: Entra en la condición con "+startCharcter);
                        int color = Color.parseColor(String.valueOf(s));
                        int r, g, b;
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
                        Log.d(TAG, "afterTextChanged: salta el error con:" + s);
                        //s.clear();
                    }
                }else {
                    Log.d(TAG, "afterTextChanged: no entiendo");
                }


            }
        };

    }
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            //int maxLength;
            //InputFilter[] filters = new InputFilter[1];
                if(item.getItemId() == R.id.toHex) {
                    //maxLength = 7;
                    //filters[0] = new InputFilter.LengthFilter(maxLength);
                    //deshabilito valores RGB
                    disableTextViewRGB();
                    disableEditTextRGB();
                    resetBarProgress();
                    disableSeekbars();
                    //habilito valores HEX
                    this.inputValues.setEnabled(true);
                    //habiito filtros para que solo haya 7 caracteres
                    //this.inputValues.setFilters(filters);
                    //le digo de que tipo es el input
                    this.inputValues.setInputType(InputType.TYPE_CLASS_TEXT);
                    //borro cualquier otro input que puede haber
                    //this.inputValues.setText("#");
                    //limpio valores anteriores
                    clearEditText();
                    //le pongo el color blanco por defecto
                    resetDemoColors();
                    this.hash.setTextColor(Color.BLACK);
                    this.inputValues.addTextChangedListener(textWatcher);
                    item.setChecked(!item.isChecked());
                    return true;
                }else if(item.getItemId() == R.id.toRGB) {
                    //le pongo el color blanco por defecto
                    resetDemoColors();
                    disableSeekbars();
                    //deshabilito los otros inputs
                    this.inputValues.setEnabled(false);
                    //habilito los de RGB
                    enableEditTextRGB();
                    activeTextViewRGB();
                    //limpio los valores anteriores
                    resetBarProgress();
                    this.inputValues.getText().clear();
                    clearEditText();
                    //implemento listeners
                    this.editTextRED.addTextChangedListener(ctwR);
                    this.editTextGREEN.addTextChangedListener(ctwG);
                    this.editTextBLUE.addTextChangedListener(ctwB);
                    item.setChecked(!item.isChecked());
                    return true;
                } else if (item.getItemId() == R.id.Bars) {
                    disableTextViewRGB();
                    this.inputValues.setEnabled(false);
                    disableEditTextRGB();
                    enableSeekBars();
                    clearEditText();
                    resetBarProgress();
                    this.inputValues.getText().clear();
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
}
