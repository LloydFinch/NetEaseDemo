package com.devloper.lloydfinch.neteasedemo.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;


/**
 * Name: User
 * Author: lloydfinch
 * Function: User bean
 * Date: 2020-01-08 14:18
 * Modify: lloydfinch 2020-01-08 14:18
 */
public class User extends BaseObservable {

    private String name;
    private int age;

    /**
     * 只有加上这个注解才会在BR中生成，才能动态刷新
     *
     * @return
     */
    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        /**
         * 通知属性改变
         */
        notifyPropertyChanged(com.devloper.lloydfinch.neteasedemo.BR.name);
    }

    @Bindable
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(com.devloper.lloydfinch.neteasedemo.BR.age);
    }
}
