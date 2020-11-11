package com.example.pixelcamera;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import java.lang.ref.WeakReference;

import static android.content.Context.MODE_PRIVATE;

public class PixelatorSettingsSaver implements Runnable {
    private WeakReference<Activity> activity;
    private PixelatorSettings settingsToSave;
    private String TAG = "PixelatorSettingsSave.java";

    public PixelatorSettingsSaver(WeakReference<Activity> activityWeakReference, PixelatorSettings settings) {
        this.settingsToSave = settings;
        this.activity = activityWeakReference;
    }

    public void run() {
        SharedPreferences mPrefs = activity.getSharedPreferences("PixelatorSettings", MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor;
        Gson gson = new Gson();
        String jsonString = gson.toJson(this.settingsToSave);
        Log.i(TAG, "Saving these Json settings:" + jsonString);

    }
}
