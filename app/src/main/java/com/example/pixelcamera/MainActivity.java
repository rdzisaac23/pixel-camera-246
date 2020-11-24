package com.example.pixelcamera;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.lang.ref.WeakReference;

import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Check for sharedPreferences for default settings
        //If none found then set to factory default settings
        //Then display the settings on the activity
        PixelatorSettingsLoader loader = new PixelatorSettingsLoader(new WeakReference<Activity>(this));
        Thread loadThread = new Thread(loader, "Settings Loader Thread");
        loadThread.start();
    }

    public void button_save(View view) {
        EditText editText = (EditText) findViewById(R.id.editTextNumber);
        int pixelSize;
        String sizeString = editText.getText().toString();
        if (sizeString.matches("")) {
            pixelSize = 5;
        }
        else {
            pixelSize = abs(parseInt(sizeString));
        }
        ToggleButton shapeToggleButton = (ToggleButton) findViewById(R.id.toggleButton); // initiate a toggle button
        boolean ToggleButtonState = shapeToggleButton.isChecked(); // check current state of a toggle button (true or false).
        String shape;
        if (ToggleButtonState) {
            shape = "circle";
        } else {
            shape = "square";
        }
        PixelatorSettingsSaver runnable = new PixelatorSettingsSaver(new WeakReference<Activity>(this), new PixelatorSettings(shape, pixelSize));
        Thread saveThread = new Thread(runnable, "Settings Saver Thread");
        saveThread.start();
    }

    public void btn_gallery(View view) {

    }

}