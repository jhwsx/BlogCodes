package com.example.layoutinflaterinflateparamstudy.resolve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.layoutinflaterinflateparamstudy.R;

public class ResolveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resolve_activity);
        LinearLayout linearLayout = findViewById(R.id.linearlayout);
        LinearLayout linearLayout2 = findViewById(R.id.linearlayout2);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.custom_button, linearLayout, false);
        linearLayout2.addView(view);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ResolveActivity.class);
        context.startActivity(starter);
    }
}