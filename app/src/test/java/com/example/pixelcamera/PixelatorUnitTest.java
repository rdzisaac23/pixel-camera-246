package com.example.pixelcamera;

import org.junit.Test;

import static org.junit.Assert.*;

public class PixelatorUnitTest {
    @Test
    public void return_pixelGrid() {
        testPixelator = new Pixelator();
        assertEquals(PixelGrid.class, getClass(testPixelator.pixelate()));
    }

    @Test
    public void resolve_singleColorImage() {

    }

    @Test
    public void reject_scaleIncrease() {

    }

    @Test
    public void load_imageBuffer() {

    }
}
