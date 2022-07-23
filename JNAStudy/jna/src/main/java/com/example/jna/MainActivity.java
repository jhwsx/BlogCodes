package com.example.jna;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.jna.databinding.ActivityMainBinding;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int add = JNALibrary.INSTANCE.add(1, 2);
        Log.d(TAG, "onCreate: add = " + add);
        int max = JNALibrary.INSTANCE.maximum(10, 9);
        Log.d(TAG, "onCreate: max = " + max);
        JNALibrary.INSTANCE.printBoolean(true, false);
        JNALibrary.INSTANCE.printChar('a');
        JNALibrary.INSTANCE.printArray(new int[]{1, 2, 3, 4}, 4);
        JNALibrary.INSTANCE.printString("Hello, JNA!");
        User.ByValue userValue = new User.ByValue("wzc", 176, 60.3);
        JNALibrary.INSTANCE.printUserValue(userValue);
        User.ByReference userReference = new User.ByReference("zcc", 164, 50.5);
        JNALibrary.INSTANCE.printUserRef(userReference);
        User.ByReference userReference2 = new User.ByReference("wjq", 70, 12.5);
        JNALibrary.INSTANCE.printUserPointer(userReference2);
//        int len = 3;
//        int shortSize = Native.getNativeSize(Short.class);
//        Pointer pointer = new Memory(len * shortSize);
//        for (int i = 0; i < len; i++) {
//            pointer.setShort(shortSize * i, (short) i);
//        }
//        ArrInfo.ByValue arrInfoByValue = new ArrInfo.ByValue(pointer, len);
//        JNALibrary.INSTANCE.testStruct(arrInfoByValue);
        IntByReference intByReference = new IntByReference();
        Log.d(TAG, "onCreate: before changeInt: " + intByReference.getValue());
        JNALibrary.INSTANCE.changeInt(intByReference);
        Log.d(TAG, "onCreate: after changeInt: " + intByReference.getValue());
        PointerByReference pointerByReference = new PointerByReference();
        JNALibrary.INSTANCE.changeString(pointerByReference);
        Log.d(TAG, "onCreate: pointerByReference.getValue().getString(0)=" + pointerByReference.getValue().getString(0));
        DownloadCallbacks.ByReference downloadCallbacks = new DownloadCallbacks.ByReference(new DownloadStartCallback() {
            @Override
            public void onDownloadStart() {
                Log.d(TAG, "onDownloadStart");
            }
        }, new DownloadProgressCallback() {
            @Override
            public void onDownloadProgress(int progress) {
                Log.d(TAG, "onDownloadProgress " + progress);
            }
        }, new DownloadFinishCallback() {
            @Override
            public void onDownloadFinish() {
                Log.d(TAG, "onDownloadFinish");
            }
        });
        JNALibrary.INSTANCE.setCallback(downloadCallbacks);
    }


}