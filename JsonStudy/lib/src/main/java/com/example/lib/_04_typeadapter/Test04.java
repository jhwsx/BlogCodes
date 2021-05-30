package com.example.lib._04_typeadapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * @author wangzhichao
 * @since 2021/5/31
 */
public class Test04 {
    public static void main(String[] args) {
        demo();
    }

    private static void demo() {
        Gson gson = new GsonBuilder().registerTypeAdapter(Point.class, new TypeAdapter<Point>() {
            @Override
            public void write(JsonWriter writer, Point value) throws IOException {
                if (value == null) {
                    writer.nullValue();
                    return;
                }
                String xy = value.x + "," + value.y;
                writer.value(xy);
            }

            @Override
            public Point read(JsonReader reader) throws IOException {
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    return null;
                }
                String xy = reader.nextString();
                String[] parts = xy.split(",");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                return new Point(x, y);
            }
        }).create();
        // 序列化

        Point point = new Point(1, 1);
        System.out.println(gson.toJson(point));
        // 反序列化
        // 特别注意：是 "\"2,2\"", 而不是 "2,2"，否则反序列化会失败
        String json = "\"2,2\"";
        Point p = gson.fromJson(json, Point.class);
        System.out.println(p);
    }
}
