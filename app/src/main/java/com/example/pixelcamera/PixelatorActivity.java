package com.example.pixelcamera;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
    View mColorView;
    TextView mColorPicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pixelator);
        Intent intent = getIntent();
        String imagePath = intent.getStringExtra(GetImageActivity.IMAGE_PATH);

        this.imageView = findViewById(R.id.ImageView);
        mColorPicked = findViewById(R.id.ColorPicked);
        mColorView = findViewById(R.id.ColorView);


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
        this.pixelator = new Pixelator(this.settings);// image view on touch

        this.imageView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE){
                this.imageView.setDrawingCacheEnabled(true);
                this.imageView.buildDrawingCache(true);
                this.current_image = this.imageView.getDrawingCache();

                int pixel = this.current_image.getPixel((int)event.getX(), (int)event.getY());

                //getting RGB values
                int r = Color.red(pixel);
                int g = Color.green(pixel);
                int b = Color.blue(pixel);
                int a = Color.alpha(pixel);

                //Hex Value
                String hex = "#"+ Integer.toHexString(pixel);

                //Background Color
                mColorView.setBackgroundColor(Color.argb(a,r,g,b));

                //String Text View
                mColorPicked.setText("RGB: "+r+", "+g+", "+b+ "\nHEX: "+hex);
            }
            return true;
        });
        Log.d(this.TAG, "End of PixelatorActivity onCreate.");
    }

    public void btn_draw_grid(View view) {
        this.current_image = this.pixelator.drawGrid(this.original_image);
        this.imageView.setImageBitmap(this.current_image);
    }

    public void btn_preview_pixelate(View view) {
        Runnable runnable = new PixelateFromImage(this.imageView, this.pixelator, this.original_image);
        Thread previewPxThread = new Thread(runnable);
        previewPxThread.start();
    }

    public void btn_apply_px(View view) {
        EditText editText = findViewById(R.id.editTextPixelSize);
        String value = editText.getText().toString();
        int size = Integer.parseInt(value);
        PixelatorSettings settings = new PixelatorSettings(size);
        this.pixelator.setSettings(settings);
        PixelatorSettingsSaver saver = new PixelatorSettingsSaver(new WeakReference<Activity>(this), settings);
    }

}