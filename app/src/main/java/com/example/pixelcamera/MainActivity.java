package com.example.pixelcamera;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


//The Unlimited Tacos collect 10! Sun 2 smiles.
//This is Luke's merge conflict.
public class MainActivity extends AppCompatActivity {
    //Let's practice resolving merge conflicts.

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

    //It won't matter which line if we change them all!
    //I think the User Interface on this thing will be tricky.
}