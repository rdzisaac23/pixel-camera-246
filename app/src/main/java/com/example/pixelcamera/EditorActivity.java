package com.example.pixelcamera;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.RecyclerView;

import java.lang.ref.WeakReference;

import static java.lang.Integer.parseInt;
import static java.lang.Math.abs;

public class EditorActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

    }
}
