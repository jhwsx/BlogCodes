package com.google.gson.internal.bind;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

abstract class BoundField {
    final String name;

    protected BoundField(String name) {
        this.name = name;
    }

    abstract boolean writeField(Object value) throws IOException, IllegalAccessException;

    abstract void write(JsonWriter writer, Object value) throws IOException, IllegalAccessException;

    abstract void read(JsonReader reader, Object value) throws IOException, IllegalAccessException;
}