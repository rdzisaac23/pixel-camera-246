package com.example.pixelcamera;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.lang.ref.WeakReference;

import static android.content.Context.MODE_PRIVATE;

/**
 * Retrieves the user's default pixelator settings.
 *
 * @author Luke Draper
 * @version 1.0
 * @since 11-17-2020
 */
public class PixelatorSettingsLoader implements Runnable {
    private WeakReference<Activity> activity;
    private PixelatorSettings settingsToLoad;
    private String TAG = "PixelatorSettingsLoader.java";
    private final String PREFERENCE_FILE_KEY = "com.example.pixelcamera.pixelator_settings";

    /**
     * @param activityWeakReference The Activity in need of the settings.
     */
    public PixelatorSettingsLoader(WeakReference<Activity> activityWeakReference) {
        this.activity = activityWeakReference;
    }

    /**
     * Loads the settings and updates views appropriately.
     */
    public void run() {
        SharedPreferences sharedPref = activity.get().getSharedPreferences(PREFERENCE_FILE_KEY, MODE_PRIVATE);
        String shape = sharedPref.getString("shape", "square");
        int size = sharedPref.getInt("size", 5);
        this.settingsToLoad = new PixelatorSettings(shape, size);
        Log.i(TAG, "Loading these settings: \nShape: " + shape + "\nSize: " + size + "\n");
        activity.get().runOnUiThread(() -> {
            ToggleButton shapeToggleButton = (ToggleButton) activity.get().findViewById(R.id.toggleButton);
            EditText editText = activity.get().findViewById(R.id.editTextNumber);
            if (shape.equals("square")) {
                shapeToggleButton.setChecked(false);
            } else if (shape.equals("circle")) {
                shapeToggleButton.setChecked(true);
            }
            String displayText = "" + size;
            editText.setText(displayText);

        });
    }

}
