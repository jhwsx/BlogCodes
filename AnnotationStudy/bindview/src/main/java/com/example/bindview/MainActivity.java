package com.example.bindview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bindview_annotations.BindView;
import com.example.bindview_api.BindViewManager;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView tv;

    @BindView(R.id.iv)
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BindViewManager.getInstance().bind(this);
        tv.setText("Happy New Year!");
        iv.setImageResource(R.mipmap.ic_launcher);
    }
}