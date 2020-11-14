package com.example.pixelcamera;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.lang.ref.WeakReference;

import static android.content.Context.MODE_PRIVATE;

public class PixelatorSettingsLoader implements Runnable {
    private WeakReference<Activity> activity;
    private PixelatorSettings settingsToLoad;
    private String TAG = "PixelatorSettingsSaver.java";
    private final String PREFERENCE_FILE_KEY = "com.example.pixelcamera.pixelator_settings";

    public PixelatorSettingsLoader(WeakReference<Activity> activityWeakReference) {
        this.activity = activityWeakReference;

    }

    public void run() {
        SharedPreferences sharedPref = activity.get().getSharedPreferences(PREFERENCE_FILE_KEY, MODE_PRIVATE);
        String shape = sharedPref.getString("shape", "square");
        int size = sharedPref.getInt("size", 5);
        this.settingsToLoad = new PixelatorSettings(shape, size);
        activity.get().runOnUiThread(() -> {
            ToggleButton shapeToggleButton = (ToggleButton) activity.get().findViewById(R.id.toggleButton);
            EditText editText = activity.get().findViewById(R.id.editTextNumber);
            if (shape == "square") {
                shapeToggleButton.setChecked(false);
            } else if (shape == "circle") {
                shapeToggleButton.setChecked(true);
            }

        });
    }

}
