package com.example.lib._02_annotation;

import com.google.gson.annotations.SerializedName;

class Response {

    public int code;
    @SerializedName("msg")
    public String message;
    @SerializedName(value = "content", alternate = {"result", "result_data"})
    public String data;

    public Response(int code, String message, String data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}