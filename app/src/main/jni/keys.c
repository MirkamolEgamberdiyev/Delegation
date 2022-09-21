#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_mirkamol_delegation_network_Server_getDevelopment(JNIEnv *env, jobject thiz) {
    return (*env)->NewStringUTF(env, "https://www.episodate.com/");
}