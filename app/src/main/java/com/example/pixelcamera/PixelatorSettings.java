package com.example.pixelcamera;

/**
 * @author Luke Draper
 * @version 1.0
 * @since 11-17-2020
 */
public class PixelatorSettings {
    private String defaultPixelShape;
    private int defaultPixelSize;
    final int DEFAULT_PIXEL_SIZE = 100;
    final String DEFAULT_PIXEL_SHAPE = "Square";


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
}
