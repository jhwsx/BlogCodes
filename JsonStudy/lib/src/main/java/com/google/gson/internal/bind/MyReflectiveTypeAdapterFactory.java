package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Primitives;
import com.google.gson.internal.reflect.ReflectionAccessor;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Type adapter that reflects over the fields and methods of a class.
 */
public final class MyReflectiveTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;
    private final ReflectionAccessor accessor = ReflectionAccessor.getInstance();

    public MyReflectiveTypeAdapterFactory(ConstructorConstructor constructorConstructor) {
        this.constructorConstructor = constructorConstructor;
    }

    @Override
    public <T> TypeAdapter<T> create(Gson gson, final TypeToken<T> type) {
        Class<? super T> raw = type.getRawType();
        if (!Object.class.isAssignableFrom(raw)) {
            return null; // it's a primitive!
        }
        ObjectConstructor<T> constructor = constructorConstructor.get(type);
        return new ReflectiveTypeAdapter<T>(constructor, getBoundFields(gson, type, raw));
    }

    /**
     * 收集以字段名为键，BoundField 为值的集合
     */
    private Map<String, BoundField> getBoundFields(Gson context, TypeToken<?> type, Class<?> raw) {
        Map<String, BoundField> result = new LinkedHashMap<>();
        if (raw.isInterface()) {
            return result;
        }
        while (raw != Object.class) {
            Field[] fields = raw.getDeclaredFields();
            for (Field field : fields) {
                accessor.makeAccessible(field);
                Type fieldType = $Gson$Types.resolve(type.getType(), raw, field.getGenericType());
                    String name = field.getName();
                    BoundField boundField = createBoundField(context, field, name,
                            TypeToken.get(fieldType));
                    result.put(name, boundField);
            }
            type = TypeToken.get($Gson$Types.resolve(type.getType(), raw, raw.getGenericSuperclass()));
            raw = type.getRawType();
        }
        return result;
    }

    /**
     * 创建 BoundField 对象，实现了字段的解析接口
     */
    private BoundField createBoundField(
            final Gson context, final Field field, final String name,
            final TypeToken<?> fieldType) {
        final boolean isPrimitive = Primitives.isPrimitive(fieldType.getRawType());
        final TypeAdapter<?> typeAdapter = context.getAdapter(fieldType);
        return new BoundField(name) {
            @SuppressWarnings({"unchecked", "rawtypes"})
            @Override
            void write(JsonWriter writer, Object value)
                    throws IOException, IllegalAccessException {
                Object fieldValue = field.get(value);
                TypeAdapter t = new TypeAdapterRuntimeTypeWrapper(context, typeAdapter, fieldType.getType());
                t.write(writer, fieldValue);
            }

            @Override
            void read(JsonReader reader, Object value)
                    throws IOException, IllegalAccessException {
                Object fieldValue = typeAdapter.read(reader);
                if (fieldValue != null || !isPrimitive) {
                    field.set(value, fieldValue);
                }
            }

            @Override
            public boolean writeField(Object value) throws IOException, IllegalAccessException {
                Object fieldValue = field.get(value);
                return fieldValue != value; // avoid recursion for example for Throwable.cause
            }
        };
    }
}
