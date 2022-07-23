#include <jni.h>
#include <string>
#include "sum.h"
#include "com_example_jni_JNITool.h"
#include <android/log.h> // ndk 工具里面的 liblog.so，用于打印

// 定义在 logcat 中输出日志的 TAG
#define TAG "WZC"
// int __android_log_print(int prio, const char* tag, const char* fmt, ...)
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, TAG, __VA_ARGS__)
extern "C"
JNIEXPORT jint JNICALL
Java_com_example_jni_JNITool_add(JNIEnv *env, jobject job, jint x, jint y) {
    return add(1, 2);
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_example_jni_JNITool_maximum(JNIEnv *env, jobject thiz, jint a, jint b) {
    return a > b ? a : b;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_jni_JNITool_printBoolean(JNIEnv *env, jobject thiz, jboolean b1, jboolean b2) {
    LOGD("printBoolean: b1=%d, b2=%d\n", b1, b2);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_jni_JNITool_printChar(JNIEnv *env, jobject thiz, jchar ch) {
    LOGD("printChar: ch=%c\n", ch);
}
extern "C"
JNIEXPORT void JNICALL
Java_com_example_jni_JNITool_printString(JNIEnv *env, jobject thiz, jstring str) {
    LOGD("printString: str=%s\n", env->GetStringUTFChars(str, nullptr));
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_jni_JNITool_printArray(JNIEnv *env, jobject thiz, jintArray arr) {
    int len = env->GetArrayLength(arr);
    jint *array = env->GetIntArrayElements(arr, nullptr);
    for (int i = 0; i < len; ++i) {
        LOGD("printArray: array[%d]=%d\n", i, array[i]);
    }
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_jni_JNITool_changeInt(JNIEnv *env, jobject thiz) {
    jclass jclazz = env->GetObjectClass(thiz);
    jfieldID iJfieldID = env->GetFieldID(jclazz, "i", "I");
    jint ji = env->GetIntField(thiz, iJfieldID);
    ji += 1000;
    env->SetIntField(thiz, iJfieldID, ji);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_jni_JNITool_changeString(JNIEnv *env, jobject thiz) {
    jclass jclazz = env->GetObjectClass(thiz);
    jfieldID strJfieldID = env->GetFieldID(jclazz, "str", "Ljava/lang/String;");
    jstring jstr = static_cast<jstring>(env->GetObjectField(thiz, strJfieldID));
    char *chars = const_cast<char *>(env->GetStringUTFChars(jstr, nullptr));
    jstring new_str = env->NewStringUTF("Hello");
    env->SetObjectField(thiz, strJfieldID, new_str);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_jni_JNITool_printUserValue(JNIEnv *env, jobject thiz, jobject user) {
    jclass userClass = env->FindClass("com/example/jni/User");
    User c_user;
    jfieldID nameJfieldID = env->GetFieldID(userClass, "name", "Ljava/lang/String;");
    jfieldID heightJfieldID = env->GetFieldID(userClass, "height", "I");
    jfieldID weightJfieldID = env->GetFieldID(userClass, "weight", "D");
    c_user.name = (char *) env->GetStringUTFChars(
            static_cast<jstring>(env->GetObjectField(user, nameJfieldID)), nullptr);
    c_user.height = env->GetIntField(user, heightJfieldID);
    c_user.weight = env->GetDoubleField(user, weightJfieldID);
    LOGD("printUserValue: c_user.name=%s, c_user.height=%d, c_user.weight=%.2f\n", c_user.name,
         c_user.height, c_user.weight);
}
extern "C"
JNIEXPORT jlong JNICALL
Java_com_example_jni_JNITool_createUser(JNIEnv *env, jobject thiz) {
    User *user = new User();
    return reinterpret_cast<jlong>(user);
}


extern "C"
JNIEXPORT void JNICALL
Java_com_example_jni_JNITool_printUser(JNIEnv *env, jobject thiz, jlong user_ptr) {
    User *user = reinterpret_cast<User *>(user_ptr);
    LOGD("printUser: user->name=%s, user->height=%d, user->weight=%.2f\n", user->name, user->height,
         user->weight);
}
extern "C"
JNIEXPORT void JNICALL
Java_com_example_jni_JNITool_configUser(JNIEnv *env, jobject thiz, jlong user_ptr, jstring name,
                                        jint height, jdouble weight) {
    User *user = reinterpret_cast<User *>(user_ptr);
    user->name = (char *) env->GetStringUTFChars(name, nullptr);
    user->height = height;
    user->weight = weight;
}