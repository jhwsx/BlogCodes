package com.example.eventbusstudy;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusBuilder;

/**
 * @author wangzhichao
 * @since 2022/4/9
 */
public class EventBus3 {

    static volatile EventBus defaultInstance;

    public static EventBus getDefault() {
        EventBus instance = defaultInstance;
        if (instance == null) {
            synchronized (EventBus.class) {
                instance = defaultInstance;
                if (instance == null) {
                    instance = defaultInstance =
                            EventBus.builder()
                                    .ignoreGeneratedIndex(false)
                                    .logSubscriberExceptions(true)
                                    .strictMethodVerification(true)
                                    .build();
                }
            }
        }
        return instance;
    }
}
