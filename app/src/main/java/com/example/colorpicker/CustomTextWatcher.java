package com.example.colorpicker;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

/**
 * @author Elena Cirstea
 * */
public class CustomTextWatcher implements TextWatcher {
    private static final int OLD_MAX_VALUE= 255;
    private static final int OLD_MIN_VALUE = 0;
    private static final float NEW_MIN_VALUE= 0;
    private static final float NEW_MAX_VALUE = 0;
    float oldRange = (OLD_MAX_VALUE- OLD_MIN_VALUE);
    float newRange= (NEW_MAX_VALUE- OLD_MIN_VALUE);
    private EditText textValue;
    private SeekBar bar;
    private Context context;
    private String color;
    private EditText inputValues;
    private View view;
    private TextView demoText;
    private String hex;
    private int colorHex, colorInt;
    private float value, newValue;
    private Color c;

    public CustomTextWatcher(EditText textValue, SeekBar bar, String color, Context context, EditText inputValues, View view, TextView demoText) {
        this.textValue = textValue;
        this.bar = bar;
        this.color = color;
        this.context = context;
        this.inputValues = inputValues;
        this.view = view;
        this.demoText = demoText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        textValue.setSelection(textValue.length());
        // a range definition is needed


        try {
            value = Float.parseFloat(s.toString());
            colorInt = ((ColorDrawable) view.getBackground()).getColor();
            c = Color.valueOf(colorInt);
            if (value < 256 && s.length() >= 1) {
                switch (color) {
                    case "red":
                        if(textValue.isEnabled()){
                            //calculates the new value in the new range
                            newValue = (((value - OLD_MIN_VALUE) * newRange)/ oldRange)+ NEW_MIN_VALUE;
                            view.setBackgroundColor(Color.rgb(newValue, c.green(), c.blue()));
                            demoText.setTextColor(Color.rgb(newValue, c.green(), c.blue()));
                            //converts the value to HEX
                            colorHex = Color.argb(0, newValue, c.green(), c.blue());
                            hex = Integer.toHexString(colorHex);
                            if (!inputValues.isEnabled()) {
                                inputValues.setText(hex);
                            }
                        }
                        break;
                    case "green":
                        if(textValue.isEnabled()){
                            newValue = (((value-0) * newRange)/ oldRange) + 0;
                            view.setBackgroundColor(Color.rgb(c.red(), newValue, c.blue()));
                            demoText.setTextColor(Color.rgb(c.red(), newValue, c.blue()));
                            //converts the value to HEX
                            colorHex = Color.argb(0, c.red(), newValue, c.blue());
                            hex = Integer.toHexString(colorHex);
                            if (!inputValues.isEnabled()) {
                                inputValues.setText(hex);
                            }
                        }
                        break;
                    case "blue":
                        if(textValue.isEnabled()){
                            newValue = (((value-0) * newRange)/ oldRange) + 0;
                            view.setBackgroundColor(Color.rgb(c.red(), c.green(), newValue));
                            demoText.setTextColor(Color.rgb(c.red(), c.green(), newValue));
                            //converts the value to HEX
                            colorHex = Color.argb(0, c.red(), c.green(), newValue);
                            hex = Integer.toHexString(colorHex);
                            if (!inputValues.isEnabled()) {
                                inputValues.setText(hex);
                            }
                        }
                        break;

                }
                bar.setProgress((int) value);
            } else if (value > 255 && s.length() == 3) {
                Toast.makeText(context, "Solo se permiten valores de 0 a 255.", Toast.LENGTH_LONG).show();
                view.setBackgroundColor(Color.WHITE);
                demoText.setTextColor(Color.BLACK);
                inputValues.getText().clear();
                textValue.getText().clear();
            }
        } catch (NumberFormatException ignored) {
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void afterTextChanged(Editable s) {

    }
}

