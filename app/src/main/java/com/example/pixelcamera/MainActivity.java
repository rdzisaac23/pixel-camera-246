package com.example.pixelcamera;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.lang.ref.WeakReference;

import static java.lang.Integer.parseInt;

//The Unlimited Tacos collect 10! Sun 2 smiles.
//This is Luke's merge conflict.
public class MainActivity extends AppCompatActivity {
    //Let's practice resolving merge conflicts.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Check for sharedPreferences for default settings
        //If none found then set to factory default settings
        //Then display the settings on the activity
    }
    //It won't matter which line if we change them all!
    //I think the User Interface on this thing will be tricky.
    public void button_save(View view) {
        EditText editText = (EditText) findViewById(R.id.editTextNumber);
        int pixelSize = parseInt(editText.getText().toString());
        ToggleButton shapeToggleButton = (ToggleButton) findViewById(R.id.toggleButton); // initiate a toggle button
        Boolean ToggleButtonState = shapeToggleButton.isChecked(); // check current state of a toggle button (true or false).
        String shape = "";
        if (ToggleButtonState) {
            shape = "circle";
        } else {
            shape = "square";
        }
        PixelatorSettingsSaver runnable = new PixelatorSettingsSaver(new WeakReference<Activity>(this), new PixelatorSettings(shape, pixelSize));
        Thread saveThread = new Thread(runnable, "Settings Saver Thread");
        saveThread.start();
    }

}