package com.devloper.lloydfinch.neteasedemo.eventbus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Name: EventBusDemo
 * Author: lloydfinch
 * Function: EventBus测试代码
 * Date: 2019-12-10 16:05
 * Modify: lloydfinch 2019-12-10 16:05
 */
public class EventBusDemo {

    public static String subscriber = "Event1";

    {
        EventBus.getDefault().register(subscriber);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEvent1(Event.Event1 event) {

    }


    public void post() {
        EventBus.getDefault().post(new Event.Event1());
    }

    @Override
    protected void finalize() throws Throwable {
        EventBus.getDefault().unregister(subscriber);
        super.finalize();
    }
}
