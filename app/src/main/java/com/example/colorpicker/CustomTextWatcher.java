package com.example.colorpicker;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.annotation.RequiresApi;

public class CustomTextWatcher implements TextWatcher {

    private static final String TAG = "CTWatcher";
    private EditText textValue, hexInput;
    private SeekBar bar;
    private View view;
    private String color;

    public CustomTextWatcher(EditText textValue, SeekBar bar, View view, String color, EditText hexInput) {
        this.textValue = textValue;
        this.bar = bar;
        this.view = view;
        this.color = color;
        this.hexInput = hexInput;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void afterTextChanged(Editable s) {
        try {
            int colorHex = 0;
            String hex = "";
            int value = Integer.parseInt(s.toString());
            int colorInt = ((ColorDrawable) view.getBackground()).getColor();
            Color c = Color.valueOf(colorInt);
            if (value < 256) {
                switch (color) {
                    case "blue":
                        view.setBackgroundColor(Color.rgb(c.red(), c.green(), value));
                        colorHex = Color.argb(0, c.red(), c.green(), value);
                        hex = Integer.toHexString(colorHex);
                        this.hexInput.setText(hex);
                        break;
                    case "green":
                        view.setBackgroundColor(Color.rgb(c.red(), value, c.green()));
                        colorHex = Color.argb(0, c.red(), value, c.green());
                        hex = Integer.toHexString(colorHex);
                        this.hexInput.setText(hex);
                        break;
                    case "red":
                        view.setBackgroundColor(Color.rgb(value, c.green(), c.blue()));
                        colorHex = Color.argb(0, value, c.green(), c.blue());
                        hex = Integer.toHexString(colorHex);
                        this.hexInput.setText(hex);
                        break;
                }
                bar.setProgress(value);
            }
        } catch (NumberFormatException ignored) {
        }
    }
}

