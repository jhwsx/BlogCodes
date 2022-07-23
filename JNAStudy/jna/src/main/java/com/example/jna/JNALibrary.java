package com.example.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/**
 * @author wangzhichao
 * @since 2022/3/10
 */
public interface JNALibrary extends Library {

    // jna 是 .so 库的名字，和 CMakeLists.txt 中的 project("jna") 保持一致。
    JNALibrary INSTANCE = Native.load("jna", JNALibrary.class);

    int add(int x, int y);

    int maximum(int a, int b);

    void printBoolean(boolean b1, boolean b2);

    void printChar(char ch);

    void printString(String str);

    void printArray(int[] arr, int length);

    // 向 C++ 层传递结构对象
    // 值传递，会拷贝一个新的对象
    void printUserValue(User.ByValue user);
    // 传递引用，传递地址，不会拷贝一个新的对象
    void printUserRef(User.ByReference user);

    void printUserPointer(User.ByReference user);

    void testStruct(ArrInfo arrInfo);
    // 在 C++ 层改变 int 值
    void changeInt(IntByReference intByReference);

    void changeString(PointerByReference pointerByReference);

    void setCallback(DownloadCallbacks callback);
}

