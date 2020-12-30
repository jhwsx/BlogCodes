package com.example.bindview_api;

import android.app.Activity;
import android.util.LruCache;

/**
 * @author wangzhichao
 * @since 2020/12/30
 */
public class BindViewManager {
    private static volatile BindViewManager instance = null;

    private static final String BINDVIEW_FILE_NAME = "$$BindView";

    private final LruCache<String, BindViewInterface> cache;

    private BindViewManager() {
        cache = new LruCache<>(100);
    }

    public static BindViewManager getInstance() {
        if (instance == null) {
            synchronized (BindViewManager.class) {
                if (instance == null) {
                    instance = new BindViewManager();
                }
            }
        }
        return instance;
    }


    public void bind(Activity activity) {
        String activityFullName = activity.getClass().getName();
        BindViewInterface bindViewInterface = cache.get(activityFullName);
        if (bindViewInterface == null) {
            String bindViewInterfaceFileFullName = activityFullName + BINDVIEW_FILE_NAME;
            try {
                Class<?> clazz = Class.forName(bindViewInterfaceFileFullName);
                bindViewInterface = (BindViewInterface) clazz.newInstance();
                cache.put(activityFullName, bindViewInterface);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (bindViewInterface != null) {
            bindViewInterface.bind(activity);
        }
    }
}
