package com.example.pixelcamera;

import com.google.gson.Gson;

public class PixelatorSettingsSaver implements Runnable {
    private PixelatorSettings settingsToSave;

    public PixelatorSettingsSaver(PixelatorSettings settings) {
        this.settingsToSave = settings;
    }

    public void run() {
        Gson gson = new Gson();
    }
}
