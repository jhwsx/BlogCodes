//
// Created by 39233 on 2022/3/10.
//
#include "sum.h"
#include <android/log.h> // ndk 工具里面的 liblog.so，用于打印
#include <cstdlib>

// 定义在 logcat 中输出日志的 TAG
#define TAG "WZC"
// int __android_log_print(int prio, const char* tag, const char* fmt, ...)
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, TAG, __VA_ARGS__)

int add(int x, int y) {
    return x + y;
}

int maximum(int a, int b) {
    return a > b ? a : b;
}

void printBoolean(int b1, int b2) {
    LOGD("printBoolean: b1=%d, b2=%d\n", b1, b2);
}
void printChar(wchar_t ch) {
    LOGD("printChar: %c\n", ch);
}

void printString(char* str) {
    LOGD("printString: %s\n", str);
}

void printArray(int* arr, int length) {
    for (int i = 0; i < length; ++i) {
        LOGD("printArray: %d\n", arr[i]);
    }
}

void printUserValue(User user) {
    LOGD("printUserValue: user.name=%s, user.height=%d, user.weight=%.2f\n", user.name, user.height, user.weight);
}

void printUserRef(User &user) {
    LOGD("printUserRef: user.name=%s, user.height=%d, user.weight=%.2f\n", user.name, user.height, user.weight);
}

void printUserPointer(User* pu) {
    LOGD("printUserPointer: pu->name=%s, pu->height=%d, pu->weight=%.2f\n", pu->name, pu->height, pu->weight);
}

void testStruct(ArrInfo arrInfo) {
    for (int i = 0; i < arrInfo.len; ++i) {
        LOGD("testStruct: arrInfo.vals[%d]=%d\n", i, arrInfo.vals[i]);
    }
}

void changeInt(int *pInt) {
    *pInt = 5;
}

void changeString(char **pStr) {
    char * str = "Hello";
    *pStr = str;
}
void setCallback(DownloadCallbacks* downloadCallbacks) {
    downloadCallbacks->downloadStartCallback();
    for (int i = 0; i < 100; i+=10) {
        downloadCallbacks->downloadProgressCallback(i);
    }
    downloadCallbacks->downloadFinishCallback();
}
