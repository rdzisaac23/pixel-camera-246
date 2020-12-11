package com.example.pixelcamera;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
//The Unlimited Tacos collect 10! Sun 2 smiles.
//This is Luke's merge conflict.
public class MainActivity extends AppCompatActivity {
    //Let's practice resolving merge conflicts.

    ImageView mImageView;
    View mColorView;
    TextView mColorPicked;

    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pickcolor);

        mImageView = findViewById(R.id.ImageView);
        mColorPicked = findViewById(R.id.ColorPicked);
        mColorView = findViewById(R.id.ColorView);

        mImageView.setDrawingCacheEnabled(true);
        mImageView.buildDrawingCache(true);

        // image view on touch

        mImageView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE){
                bitmap = mImageView.getDrawingCache();

                int pixel = bitmap.getPixel((int)event.getX(), (int)event.getY());

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

    }
    //It won't matter which line if we change them all!
    //I think the User Interface on this thing will be tricky.
}