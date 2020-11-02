package com.example.colorpicker;

import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class CustomTextWatcher implements TextWatcher {

    private EditText textValue;
    private SeekBar bar;
    private Context context;
    private String color;


    public CustomTextWatcher(EditText textValue, SeekBar bar, String color, Context context) {
        this.textValue = textValue;
        this.bar = bar;
        this.color = color;
        this.context = context;
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
            if (value < 256 && s.length() >= 1) {
                switch (color) {
                    case "blue":
                    case "green":
                    case "red":
                        break;
                }
                bar.setProgress(value);
            } else if (value > 255 && s.length() == 3) {
                Toast.makeText(context, "Solo se permiten v  ", Toast.LENGTH_LONG).show();
                textValue.getText().clear();
            }
        } catch (NumberFormatException ignored) {
        }
    }
}

