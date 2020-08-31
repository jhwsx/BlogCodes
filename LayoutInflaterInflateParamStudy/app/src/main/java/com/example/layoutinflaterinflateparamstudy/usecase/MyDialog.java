package com.example.layoutinflaterinflateparamstudy.usecase;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.layoutinflaterinflateparamstudy.R;

import static android.view.WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;

/**
 * @author wangzhichao
 * @date 20-8-12
 */
public class MyDialog extends AlertDialog {
    private static final String TAG = "MyDialog";

    public MyDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);
        Window window = this.getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        window.addFlags(FLAG_WATCH_OUTSIDE_TOUCH);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        Log.d(TAG, "onTouchEvent: " + MotionEvent.actionToString(event.getAction()));
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            dismiss();
        }
        return super.onTouchEvent(event);
    }

}
