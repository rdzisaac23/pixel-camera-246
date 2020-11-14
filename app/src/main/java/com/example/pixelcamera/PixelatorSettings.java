package com.example.pixelcamera;

import com.google.gson.annotations.SerializedName;

public class PixelatorSettings {
    @SerializedName("shape")
    private String defaultPixelShape = "square";
    @SerializedName("size")
    private int defaultPixelSize = 5;

    public PixelatorSettings (String shape, int size) {
        this.defaultPixelShape = shape;
        this.defaultPixelSize = size;
    }

    public int getDefaultPixelSize() {
        return defaultPixelSize;
    }

    public void setDefaultPixelSize(int defaultPixelSize) {
        this.defaultPixelSize = defaultPixelSize;
    }

    public String getDefaultPixelShape() {
        return defaultPixelShape;
    }

    public void setDefaultPixelShape(String defaultPixelShape) {
        this.defaultPixelShape = defaultPixelShape;
    }
}
