/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_example_jni_JNITool */

#ifndef _Included_com_example_jni_JNITool
#define _Included_com_example_jni_JNITool
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_example_jni_JNITool
 * Method:    add
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_example_jni_JNITool_add
        (JNIEnv *, jobject, jint, jint);

JNIEXPORT jint JNICALL
Java_com_example_jni_JNITool_maximum(JNIEnv *env, jobject thiz, jint a, jint b);

JNIEXPORT void JNICALL
Java_com_example_jni_JNITool_printBoolean(JNIEnv *env, jobject thiz, jboolean b1, jboolean b2);


JNIEXPORT void JNICALL
Java_com_example_jni_JNITool_printChar(JNIEnv *env, jobject thiz, jchar ch);

JNIEXPORT void JNICALL
Java_com_example_jni_JNITool_printString(JNIEnv *env, jobject thiz, jstring str);

JNIEXPORT void JNICALL
Java_com_example_jni_JNITool_printArray(JNIEnv *env, jobject thiz, jintArray arr);

JNIEXPORT void JNICALL
Java_com_example_jni_JNITool_changeInt(JNIEnv *env, jobject thiz);

JNIEXPORT void JNICALL
Java_com_example_jni_JNITool_changeString(JNIEnv *env, jobject thiz);
#ifdef __cplusplus
}
#endif
#endif