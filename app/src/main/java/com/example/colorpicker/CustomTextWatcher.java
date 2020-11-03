package com.example.colorpicker;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class CustomTextWatcher implements TextWatcher {

    private EditText textValue;
    private SeekBar bar;
    private Context context;
    private String color;
    private EditText inputValues;
    private View view;
    private TextView demoText;

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

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void afterTextChanged(Editable s) {
        try {
            int value = Integer.parseInt(s.toString());
            int colorInt = ((ColorDrawable) view.getBackground()).getColor();
            Color c = Color.valueOf(colorInt);
            if (value < 256 && s.length() >= 1) {
                switch (color) {
                    case "blue":
                        if(textValue.isEnabled()){
                            view.setBackgroundColor(Color.rgb(c.red(), c.green(), value));
                            demoText.setTextColor(Color.rgb(c.red(), c.green(), value));
                        }

                         break;
                    case "green":
                        if(textValue.isEnabled()){
                            view.setBackgroundColor(Color.rgb(c.red(), value, c.green()));
                            demoText.setTextColor(Color.rgb(c.red(), value, c.green()));
                        }
                        break;
                    case "red":
                        if(textValue.isEnabled()){
                            view.setBackgroundColor(Color.rgb(value, c.green(), c.blue()));
                            demoText.setTextColor(Color.rgb(value, c.green(), c.blue()));
                        }
                        break;
                }
                bar.setProgress(value);
            } else if (value > 255 && s.length() == 3) {
                Toast.makeText(context, "Solo se permiten valores de 0 a 255.", Toast.LENGTH_LONG).show();
                inputValues.getText().clear();
                textValue.getText().clear();
            }
        } catch (NumberFormatException ignored) {
        }
    }
}

