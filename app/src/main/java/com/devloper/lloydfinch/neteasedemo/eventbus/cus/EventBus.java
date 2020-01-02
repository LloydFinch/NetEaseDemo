package com.devloper.lloydfinch.neteasedemo.eventbus.cus;

/**
 * 采取单例实现
 */
public class EventBus {
    private static volatile EventBus instance;

    private EventBus() {
    }

    public static EventBus getInstance() {
        if (instance == null) {
            synchronized (EventBus.class) {
                if (instance == null) {
                    instance = new EventBus();
                }
            }
        }
        return instance;
    }

}
