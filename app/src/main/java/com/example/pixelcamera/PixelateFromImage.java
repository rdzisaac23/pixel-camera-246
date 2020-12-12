package com.example.pixelcamera;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class PixelateFromImage implements Runnable {
    private Pixelator pixelator;
    private Bitmap starting_image;
    private WeakReference<ImageView> weakReference;

    public PixelateFromImage (ImageView imageView, Pixelator pixelator, Bitmap bitmap){
        this.pixelator = pixelator;
        this.starting_image = bitmap;
        this.weakReference = new WeakReference<ImageView>(imageView);
    }

    @Override
    public void run() {
        final Bitmap bitmap = this.pixelator.pixelatePreview(this.starting_image);
        ImageView imageView = weakReference.get();
        imageView.post(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(bitmap);
            }
        });

    }
}
