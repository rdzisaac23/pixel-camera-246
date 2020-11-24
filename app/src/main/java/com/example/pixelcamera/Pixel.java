package com.example.pixelcamera;

/**
 * An individual visual unit represented with a color and transparency.
 *
 * @author Erik Butcher
 * @version 1.0
 * @since 11-24-2020
 */
public class Pixel {
    private String color;
    private boolean transparency;

    /**
     * @param pixelColor The initial color of the pixel.
     * @param pixelTransparency The initial transparency setting of the pixel.
     */
    Pixel(String pixelColor, boolean pixelTransparency) {
        color = pixelColor;
        transparency = pixelTransparency;
    }

    /**
     * Retrieves the color of the pixel.
     * @return A hex code color.
     */
    public String getColor() {
        return color;
    }

    /**
     * Answers whether the pixel will display its own color or the background color.
     * @return whether the pixel's color will be displayed.
     */
    public boolean willDisplay() {
        return !transparency;
    }
}
