package com.example.jni;

/**
 * @author wangzhichao
 * @since 2022/3/10
 */
public class JNITool {
    // Used to load the 'jni' library on application startup.
    static {
        System.loadLibrary("jni");
    }

    public native int add(int x, int y);
    public native int maximum(int a, int b);
    public native void printBoolean(boolean b1, boolean b2);
    public native void printChar(char ch);
    public native void printString(String str);
    public native void printArray(int[] arr);
    public native void printUserValue(User user);
    public native long createUser();
    public native void configUser(long userPtr, String name, int height, double weight);
    public native void printUser(long userPtr);
    public int i = 0;
    public native void changeInt();

    public String str = "Hi";
    public native void changeString();
}
