package com.example.pixelcamera;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void btn_gallery(View view) {
        android.util.Log.d("$className$", "$METHOD_NAME$: $content$");
        Intent intent = new Intent(this, GetImageActivity.class);
        startActivity(intent);
    }


}