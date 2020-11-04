package com.example.pixelcamera;

import org.junit.Test;

import static org.junit.Assert.*;

public class PixelUnitTest {
    @Test
    public void create_withColor() {
        assertEquals("0x7d16eb", new Pixel("0x7d16eb", true).getColor());
    }

    @Test
    public void create_Transparent() {
        assertEquals(false, new Pixel("0xffffff", false).willDisplay());
    }
}
