package com.example.eventbusstudy;

import org.greenrobot.eventbus.EventBus;

/**
 * @author wangzhichao
 * @since 2022/4/9
 */
public class EventBus2 {

    static volatile EventBus defaultInstance;

    public static EventBus getDefault() {
        EventBus instance = defaultInstance;
        if (instance == null) {
            synchronized (EventBus.class) {
                instance = defaultInstance;
                if (instance == null) {
                    instance = defaultInstance = new EventBus();
                }
            }
        }
        return instance;
    }
}
