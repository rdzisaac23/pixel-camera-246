package com.example.pixelcamera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;

public class PixelatorActivity extends AppCompatActivity {
    private final String PREFERENCE_FILE_KEY = "com.example.pixelcamera.pixelator_settings";
    private final String TAG = "PixelatorActivity";
    private PixelatorSettings settings;
    private Pixelator pixelator;
    private Bitmap original_image;
    private Bitmap current_image;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pixelator);
        Intent intent = getIntent();
        String imagePath = intent.getStringExtra(GetImageActivity.IMAGE_PATH);

        Log.i(this.TAG, "Image File Path: " + imagePath);

        try {
            FileInputStream is = new FileInputStream((new File(imagePath)));
            this.original_image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.imageView = findViewById(R.id.imageViewPixelator);
        imageView.setImageBitmap(original_image);
        this.current_image = this.original_image;


        EditText editText = findViewById(R.id.editTextPixelSize);
        SharedPreferences sharedPref = getSharedPreferences(PREFERENCE_FILE_KEY, MODE_PRIVATE);
        String shape = sharedPref.getString("shape", "square");
        int size = sharedPref.getInt("size", 100);
        editText.setText(Integer.toString(size));
        this.settings = new PixelatorSettings(shape, size);
        this.pixelator = new Pixelator(this.settings);
        Log.d(this.TAG, "End of PixelatorActivity onCreate.");
    }

    public void btn_draw_grid(View view) {
        this.current_image = this.pixelator.drawGrid(this.current_image);
        this.imageView.setImageBitmap(this.current_image);
    }

    public void btn_preview_pixelate(View view) {
        Runnable runnable = new PixelateFromImage(this.imageView, this.pixelator, this.original_image);
        Thread previewPxThread = new Thread(runnable);
        previewPxThread.start();


    }

}