package com.example.layoutinflaterinflateparamstudy.usecase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.layoutinflaterinflateparamstudy.R;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewItemInflateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view_item_inflate_activity);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, getData());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, RecycleViewItemInflateActivity.class);
        context.startActivity(starter);
    }

    private List<String> getData() {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            result.add("Item " + i);
        }
        return result;
    }
}