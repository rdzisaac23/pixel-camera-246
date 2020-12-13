package com.example.pixelcamera;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * Allows a user to select a picture and submit it for pixelation.
 *
 * @author Luke Draper
 * @version 1.0
 *
 */
public class GetImageActivity extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 123;
    private static final int WRITE_REQUEST_CODE = 557;
    public static final String IMAGE_URI = "com.example.pixelcamera.IMAGE_URI";
    public static final String IMAGE_PATH = "com.example.pixelcamera.IMAGE_PATH";
    private static final String TAG = "GetImageActivity.java";

    ImageView imageView;
    Button btn_gallery;
    String imagePath = "";
    Uri imageData = null;


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
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, GALLERY_REQUEST_CODE);
                checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, WRITE_REQUEST_CODE);

                Intent intent = new Intent();
                intent.setType("image/^");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pick an image"), GALLERY_REQUEST_CODE);
            }
        });

    }

    /**
     * Checks an arbitrary permission via its request code and permission name.
     *
     * @param permission The permission being requested.
     * @param requestCode An app-specific integer request code corresponding to that permission.
     */
    public void checkPermission(String permission, int requestCode)
    {

        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(GetImageActivity.this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(GetImageActivity.this, new String[] { permission }, requestCode);
        }
        else {
            Toast.makeText(GetImageActivity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Displays the image selected from the gallery when it is returned. And stores the image's filepath
     * in a class variable.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {

            this.imageData = data.getData();
//            this.imageFilePath = imageData.getPath();

            imageView.setImageURI(this.imageData);
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(imageData, filePath, null, null, null);
            cursor.moveToFirst();
            this.imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
            cursor.close();
        }
    }

    public void submitImage(View view) {
        if (!this.imagePath.equals("")) {

            Intent pixelatorIntent = new Intent(this, PixelatorActivity.class);
            pixelatorIntent.putExtra(IMAGE_PATH, this.imagePath);
            startActivity(pixelatorIntent);

        }
    }



}