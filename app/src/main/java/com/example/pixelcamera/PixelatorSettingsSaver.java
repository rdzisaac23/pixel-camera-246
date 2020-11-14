package com.example.pixelcamera;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.lang.ref.WeakReference;

import static android.content.Context.MODE_PRIVATE;
import static android.provider.Settings.System.getString;

public class PixelatorSettingsSaver implements Runnable {
    private WeakReference<Activity> activity;
    private PixelatorSettings settingsToSave;
    private String TAG = "PixelatorSettingsSaver.java";
    private final String PREFERENCE_FILE_KEY = "com.example.pixelcamera.pixelator_settings";

    public PixelatorSettingsSaver(WeakReference<Activity> activityWeakReference, PixelatorSettings settings) {
        this.settingsToSave = settings;
        this.activity = activityWeakReference;
    }

    public void run() {
        SharedPreferences sharedPref = activity.get().getSharedPreferences(PREFERENCE_FILE_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        /*
        Gson gson = new Gson();
        String jsonString = gson.toJson(this.settingsToSave);
        Log.i(TAG, "Saving these Json settings:" + jsonString);
        */
        Log.i(TAG, "Saving these settings:\n" + "shape: " + settingsToSave.getDefaultPixelShape() +
                    "\n\tsize: " + settingsToSave.getDefaultPixelSize());
        editor.putString("shape", settingsToSave.getDefaultPixelShape());
        editor.putInt("size", settingsToSave.getDefaultPixelSize());
        editor.apply();
        activity.get().runOnUiThread(() ->{
            Toast toast = Toast.makeText(activity.get(), "Settings saved.", Toast.LENGTH_LONG);
            toast.show();

        });
    }
}
