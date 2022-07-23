//
// Created by 39233 on 2022/3/10.
//

#ifndef _WER_H
#define _WER_H

#include <stdint.h>

#ifdef __cplusplus
extern "C" {
#endif

typedef void(*DownloadStartCallback)(void);
typedef void(*DownloadProgressCallback)(int);
typedef void(*DownloadFinishCallback)(void);

typedef struct{
    DownloadStartCallback downloadStartCallback;
    DownloadProgressCallback downloadProgressCallback;
    DownloadFinishCallback downloadFinishCallback;
} DownloadCallbacks;
int add(int x, int y);

int maximum(int a, int b);

void printBoolean(int b1, int b2);

void printChar(wchar_t ch);

void printString(char *str);

void printArray(int *arr, int length);

struct User {
    char *name;
    int height;
    double weight;
};

struct ArrInfo {
    uint16_t *vals;
    int len;
};
void printUserValue(User user);
void printUserRef(User &user);
void printUserPointer(User *pu);
void testStruct(ArrInfo arrInfo);
void changeInt(int *pInt);
void changeString(char **pStr);
void setCallback(DownloadCallbacks* downloadCallbacks);
#ifdef __cplusplus
}
#endif
#endif //_WER_H
