package com.example.layoutinflaterinflateparamstudy.usecase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.layoutinflaterinflateparamstudy.R;

public class CustomViewInflateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view_inflate_activity);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, CustomViewInflateActivity.class);
        context.startActivity(starter);
    }
}