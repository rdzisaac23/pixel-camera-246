package com.example.pixelcamera;

public class Pixel {
    private String color;
    private boolean transparency;

    Pixel(String pixelColor, boolean pixelTransparency) {
        color = pixelColor;
        transparency = pixelTransparency;
    }

    String getColor() {
        return color;
    }

    boolean willDisplay() {
        return !transparency;
    }
}
