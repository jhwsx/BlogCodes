package com.google.gson.internal.bind;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Map;

public class ReflectiveTypeAdapter<T> extends TypeAdapter<T> {
    private final ObjectConstructor<T> constructor;
    private final Map<String, BoundField> boundFields;

    ReflectiveTypeAdapter(ObjectConstructor<T> constructor, Map<String, BoundField> boundFields) {
        this.constructor = constructor;
        this.boundFields = boundFields;
    }

    @Override
    public T read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }

        T instance = constructor.construct();

        try {
            in.beginObject();
            while (in.hasNext()) {
                String name = in.nextName();
                BoundField field = boundFields.get(name);
                if (field == null) {
                    in.skipValue();
                } else {
                    field.read(in, instance);
                }
            }
        } catch (IllegalStateException e) {
            throw new JsonSyntaxException(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        }
        in.endObject();
        return instance;
    }

    @Override
    public void write(JsonWriter out, T value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        out.beginObject();
        try {
            for (BoundField boundField : boundFields.values()) {
                if (boundField.writeField(value)) {
                    out.name(boundField.name);
                    boundField.write(out, value);
                }
            }
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        }
        out.endObject();
    }
}