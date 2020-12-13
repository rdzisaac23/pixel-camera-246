package com.example.pixelcamera;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import static android.content.Context.MODE_PRIVATE;

/**
 * @author Luke Draper
 * @version 1.0
 * @since 11-17-2020
 */
public class PixelatorSettings {
    private static final String PREFERENCE_FILE_KEY = "com.example.pixelcamera.pixelator_settings";
    private static final String TAG = "PixelatorSettings.java";
    private String defaultPixelShape;
    private int defaultPixelSize;
    final static int DEFAULT_PIXEL_SIZE = 100;
    final static String DEFAULT_PIXEL_SHAPE = "Square";


    /**
     * Creates a pixelator settings object with the shape and size
     *  passed in as parameters.
     * @param shape
     * @param size
     */
    public PixelatorSettings (String shape, int size) {
        this.defaultPixelShape = shape;
        this.defaultPixelSize = size;
    }

    /**
     * Creates a PixelatorSettings object with shape and size determined by constants.
     */
    public PixelatorSettings () {
        this.defaultPixelSize = DEFAULT_PIXEL_SIZE;
        this.defaultPixelShape = DEFAULT_PIXEL_SHAPE;
    }

    /**
     * Creates PixelatorSettings object from given size with the shape determined by constant.
     * @param size
     */
    public PixelatorSettings (int size) {
        this.defaultPixelSize = size;
        this.defaultPixelShape = DEFAULT_PIXEL_SHAPE;
    }

    /**
     * Returns the integer of the current size to use as default pixel size.
     * @return int defaultPixelSize
     */
    public int getDefaultPixelSize() {
        return defaultPixelSize;
    }

    /**
     * Sets the default pixel size to the parameter that is passed
     * in as an integer.
     * @param defaultPixelSize
     */
    public void setDefaultPixelSize(int defaultPixelSize) {
        this.defaultPixelSize = defaultPixelSize;
    }

    /**
     * Returns the pixel shape.
     * @return defaultPixelShape
     */
    public String getDefaultPixelShape() {
        return defaultPixelShape;
    }

    /**
     * Sets the default pixel shape to be what the parameter that is passed in is.
     * @param defaultPixelShape
     */
    public void setDefaultPixelShape(String defaultPixelShape) {
        this.defaultPixelShape = defaultPixelShape;
    }

    public static PixelatorSettings loadSettings(Activity activity) {
        SharedPreferences sharedPref = activity.getSharedPreferences(PREFERENCE_FILE_KEY, MODE_PRIVATE);
        String shape = sharedPref.getString("shape", DEFAULT_PIXEL_SHAPE);
        int size = sharedPref.getInt("size", DEFAULT_PIXEL_SIZE);
        PixelatorSettings settingsToLoad = new PixelatorSettings(shape, size);
        Log.i(TAG, "Loading these settings: \nShape: " + shape + "\nSize: " + size + "\n");
        return settingsToLoad;
    }
}
