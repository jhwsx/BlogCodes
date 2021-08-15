package com.example.javaiostudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            File tempFile = File.createTempFile("temp", ".tmp");
            Log.d(TAG, "onCreate: " + tempFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}