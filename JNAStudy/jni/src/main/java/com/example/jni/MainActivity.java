package com.example.jni;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.jni.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        JNITool jniTool = new JNITool();
        int result = jniTool.add(1, 2);
        Log.d(TAG, "onCreate: result = " + result);
        Log.d(TAG, "onCreate: max = " + jniTool.maximum(2, 10));
        jniTool.printBoolean(true, false);
        jniTool.printChar('a');
        jniTool.printString("Hello, World!");
        jniTool.printArray(new int[] {1, 2, 3, 4});
        jniTool.printUserValue(new User("wzc", 176, 60.5));
        long userPtr = jniTool.createUser();
        jniTool.configUser(userPtr, "zcc", 164, 55.5);
        jniTool.printUser(userPtr);
        Log.d(TAG, "onCreate: before change, i=" + jniTool.i);
        jniTool.changeInt();
        Log.d(TAG, "onCreate: after change, i=" + jniTool.i);
        Log.d(TAG, "onCreate: before change, str=" + jniTool.str);
        jniTool.changeString();
        Log.d(TAG, "onCreate: after change, str=" + jniTool.str);
    }



}