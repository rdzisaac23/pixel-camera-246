package com.example.pixelcamera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


/**
 * Allows a user to select a picture and submit it for pixelation.
 *
 * @author Luke Draper
 * @version 1.0
 * @date 2020-11-24
 *
 */
public class GetImageActivity extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 123;

    ImageView imageView;
    Button btn_gallery;

    /**
     * Creates the activity layout and creates listeners for all buttons.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_image);
        btn_gallery = findViewById(R.id.button_gallery);
        imageView = findViewById(R.id.imageView);

        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/^");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pick an image"), GALLERY_REQUEST_CODE);
            }
        });

    }

    /**
     * Displays the image selected from the gallery when it is returned.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri imageData = data.getData();

            imageView.setImageURI(imageData);
        }

    }
}