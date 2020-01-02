package com.devloper.lloydfinch.neteasedemo.eventbus.cus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述订阅者
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscribe {
    ThreadMode threadMode() default ThreadMode.POSTING;
}
