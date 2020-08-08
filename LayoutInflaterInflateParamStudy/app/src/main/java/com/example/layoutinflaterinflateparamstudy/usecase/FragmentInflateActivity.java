package com.example.layoutinflaterinflateparamstudy.usecase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.layoutinflaterinflateparamstudy.R;

public class FragmentInflateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_inflate_activity);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_container, MyFragment.newInstance())
                .commit();
    }
    public static void start(Context context) {
        Intent starter = new Intent(context, FragmentInflateActivity.class);
        context.startActivity(starter);
    }
}