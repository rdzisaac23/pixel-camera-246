package com.example.pixelcamera;

public class PixelatorSettings {
    private String defaultPixelShape = "square";
    private int defaultPixelSize = 10;

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
