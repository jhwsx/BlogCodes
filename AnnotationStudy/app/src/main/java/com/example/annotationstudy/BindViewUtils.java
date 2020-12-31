package com.example.annotationstudy;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

/**
 * @author wangzhichao
 * @since 2020/12/31
 */
public class BindViewUtils {
    public static void bind(Activity activity) {
        Class<? extends Activity> activityClass = activity.getClass();
        Field[] declaredFields = activityClass.getDeclaredFields();
        for (Field field : declaredFields) {
            BindView annotation = field.getAnnotation(BindView.class);
            if (annotation != null) {
                int id = annotation.value();
                View view = activity.findViewById(id);
                field.setAccessible(true);
                try {
                    field.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
