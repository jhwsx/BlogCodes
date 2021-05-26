package com.example.lib.basic;

class Response<T> {
    public int code;
    public String message;
    public T data;

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}