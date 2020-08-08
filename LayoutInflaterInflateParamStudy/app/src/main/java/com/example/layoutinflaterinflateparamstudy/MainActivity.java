package com.example.layoutinflaterinflateparamstudy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.layoutinflaterinflateparamstudy.resolve.ResolveActivity;
import com.example.layoutinflaterinflateparamstudy.usecase.CustomViewInflateActivity;
import com.example.layoutinflaterinflateparamstudy.usecase.FragmentInflateActivity;
import com.example.layoutinflaterinflateparamstudy.usecase.RecycleViewItemInflateActivity;

/**
 * 参考文章:
 * https://www.bignerdranch.com/blog/understanding-androids-layoutinflater-inflate/
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_resolve).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResolveActivity.start(MainActivity.this);
            }
        });
        findViewById(R.id.btn_customview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomViewInflateActivity.start(MainActivity.this);
            }
        });
        findViewById(R.id.btn_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentInflateActivity.start(MainActivity.this);
            }
        });
        findViewById(R.id.btn_recycleview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecycleViewItemInflateActivity.start(MainActivity.this);
            }
        });
        findViewById(R.id.btn_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog, null);
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("AlertDialog")
                        .setView(view)
                        .create();
                alertDialog.show();
            }
        });
    }
}