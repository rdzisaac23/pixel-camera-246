package com.example.pixelcamera;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.File;

/**
 * A class that Pixelates an image.
 *
 * @author Luke Draper
 * @version 1.0
 * @since 12-5-2020
 */
public class Pixelator {
    private PixelatorSettings settings;
    static final String TAG = "Pixelator.java";


    public Pixelator(PixelatorSettings settings) {
        this.settings = settings;
    }

    /**
     *
     * @param bitmap
     * @return
     */
    public Bitmap drawGrid(Bitmap bitmap) {
        /**
         * Note that multiple createBitmap overloaded functions must be overloaded by the variable bitmap to draw
         * bitmap: original image
         */
        Bitmap copy = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888); //Important
        Canvas canvas = new Canvas(copy); //Create canvas
        Paint paint = new Paint(); //brush
        paint.setStrokeWidth(1); //Set the line width. Unit is pixel
        paint.setAntiAlias(true); //anti-aliasing
        paint.setColor(Color.GRAY); //brush color
        canvas.drawBitmap(bitmap, new Matrix(), paint); //Draw a picture identical to the bitmap on the canvas
                 // According to the size of the Bitmap, draw grid lines
                 // draw horizontal line
        for (int i = 0; i < bitmap.getHeight() / this.settings.getDefaultPixelSize(); i++) {
            canvas.drawLine(0, i * this.settings.getDefaultPixelSize(), bitmap.getWidth(), i * this.settings.getDefaultPixelSize(), paint);
        }
        // draw vertical lines
        for (int i = 0; i < bitmap.getWidth() / this.settings.getDefaultPixelSize(); i++) {
            canvas.drawLine(i * this.settings.getDefaultPixelSize(), 0, i * this.settings.getDefaultPixelSize(), bitmap.getHeight(), paint);
        }
        return copy;
    }

    public Bitmap pixelatePreview(Bitmap bitmap) {
        Bitmap copy = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888); //Important
        Canvas canvas = new Canvas(copy); //Create canvas
        Paint paint = new Paint(); //brush
        paint.setStrokeWidth(1); //Set the line width. Unit is pixel
        paint.setAntiAlias(true); //anti-aliasing
        paint.setColor(Color.WHITE); //brush color
        int size = this.settings.getDefaultPixelSize();
        Log.i(TAG, "Image width: " + bitmap.getWidth() + "   Image Height: " + bitmap.getHeight());
        for (int i = 0; i * size < bitmap.getHeight(); i++) {
            for (int j = 0; j * size < bitmap.getWidth(); j++) {
                int x = j * size;
                int y = i * size;
                Log.i(TAG, " x: " + x + "   y: " + y);
                paint.setColor(getAverageRGBSquare(bitmap, x, y, size));
                canvas.drawRect(x, y, x + size , y + size, paint);

            }
        }
        return drawGrid(copy);

    }

    /**
     * Gets the average color of a square where x, y are the coordinates of the pixel at the top left corner
     * Side length is measured in pixels.
     * Returns the color as an int to keep the required android API level lower.
     * @param bitmap
     * @param x
     * @param y
     * @param sideLength
     * @returns rgba
     */
    public int getAverageRGBSquare(Bitmap bitmap, int x, int y, int sideLength) {
        Log.i(TAG, "Image width: " + bitmap.getWidth() + "   Image Height: " + bitmap.getHeight());

        float r = 0;
        float g = 0;
        float b = 0;
        float a = 0;
        int num = 0;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        for (int i = x; i - x < sideLength; i++) {
            for (int j = y; j - y < sideLength; j++) {

                if (!(i < x || i >= width || j < y || j >= height)) {
                    int pixel = bitmap.getPixel(i, j);
                    r += Color.red(pixel);
                    g += Color.green(pixel);
                    b += Color.blue(pixel);
                    a += Color.alpha(pixel);
                    num++;
                }
            }
        }
        a = Math.round(a/num);
        r = Math.round(r/num);
        g = Math.round(g/num);
        b = Math.round(b/num);
        return getIntFromColor(a, r, g, b);
    }

    public int getIntFromColor(float Alpha, float Red, float Green, float Blue){
        int A = Math.round(Alpha);
        int R = Math.round(Red);
        int G = Math.round(Green);
        int B = Math.round(Blue);

        A = 0xFF000000;
        R = (R << 16) & 0x00FF0000;
        G = (G << 8) & 0x0000FF00;
        B = B & 0x000000FF;

        return A | R | G | B;
    }

    public void setSettings(PixelatorSettings settings) {
        this.settings = settings;
    }



}
