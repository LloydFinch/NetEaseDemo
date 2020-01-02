package com.devloper.lloydfinch.neteasedemo.RxJava;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.internal.util.InternalObservableUtils;
import rx.schedulers.Schedulers;

public class RxJavaDemo {

    private static final String TAG = "RxJavaDemo";
    private static final String Line = "======================================================================================================================";

    public void test() {
//        rx1();
//        testDemo();
//        testScheduler();
//        testChangeEvent();
//        testOtherAPI();

        //后测
        testRecall();
    }

    /**
     * 基本流程
     */
    private void rx1() {
        /**
         * 创建观察者Observer[内置有Subscriber]
         */
        Observer<Event1> observer = new Observer<Event1>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onNext(Event1 event1) {
                Log.e(TAG, "onNext");
            }
        };

        /**
         * 内置的扩展的观察者
         */
        Subscriber<Event1> subscriber = new Subscriber<Event1>() {

            @Override
            public void onStart() {
                Log.e(TAG, "onStart");
            }

            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onNext(Event1 event1) {
                Log.e(TAG, "onNext");
            }
        };

        /**
         * 创建被观察者(第二个参数是个action，会在订阅的时候触发里面的call方法)
         * 被观察者有很多很风骚的创建API
         */
        Observable<Event1> observable = Observable.create(new Observable.OnSubscribe<Event1>() {
            @Override
            public void call(Subscriber<? super Event1> subscriber) {
                Log.e(TAG, "被订阅了");
                /**
                 * 这里手动模拟一下事件
                 * onComplete()和onError()是互斥的并且是对立的
                 */
                subscriber.onNext(new Event1());
                boolean error = new Random().nextBoolean();
                if (error) {
                    subscriber.onError(new Throwable("costume error!!!"));
                } else {
                    subscriber.onCompleted();
                }
            }
        });

        /**
         * 订阅(这里是被观察者订阅观察者，这个梗要理清)。
         * 这里面会将观察者转换为Subscriber(如果不是的话)，然后会调用Subscriber的onStart()方法，然后触发被观察者的订阅触发器的call方法
         */
//        observable.subscribe(observer);
        observable.subscribe(subscriber);

    }

    /**
     * 各种风骚的创建被观察者的API和简易的观察者
     */
    private void rxObservable() {
        /**
         * 传递1-10个参数
         * 被订阅的时候按照顺序发送事件，最多支持10个，最少一个
         * 实现:将事件保存下来，call内部按照顺序发送即可
         */
        Observable<Event1> just = Observable.just(new Event1("event1"));
        Observable<Event1> just2 = Observable.just(new Event1("event1"), new Event1("event2"));

        /**
         * 跟just功能一样，传递集合或数组
         */
        List<Event1> event1List = new ArrayList<>();
        Observable<Event1> from = Observable.from(event1List); //集合
        Event1[] event1s = new Event1[]{new Event1("event1"), new Event1("event2")};
        Observable<Event1> from2 = Observable.from(event1s); //数组
    }

    /**
     * 非完整定义的观察者
     */
    private void rxSimpleObservable() {
        /**
         * 只有onNext()
         */
        Action1<Event1> onNextAction = new Action1<Event1>() {
            @Override
            public void call(Event1 event1) {

            }
        };

        /**
         * 只有onError()
         */
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        };

        /**
         * 只有onComplete()
         */
        Action0 onCompleteAction = new Action0() {
            @Override
            public void call() {

            }
        };

        /**
         * 可以只有onNext()，onError()和onComplete()为可选的
         * 会按照传入的参数个数在内部自己构造onError()和onComplete()从而创建一个{@link rx.internal.util.ActionSubscriber}
         * onError:{@link InternalObservableUtils.ERROR_NOT_IMPLEMENTED}
         * onComplete:{@link rx.functions.Actions.EMPTY_ACTION}
         */
        Observable<Event1> just = Observable.just(new Event1("event1"));
        just.subscribe(onNextAction);
        just.subscribe(onNextAction, onErrorAction);
        just.subscribe(onNextAction, onErrorAction, onCompleteAction);
    }


    /**
     * 测试调用的demo
     */
    private void testDemo() {
        /**
         * 最简单的调用
         */
        String[] strs = new String[]{"1", "2", "3"};
        Observable.from(strs).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                //这个就是onNext()
                Log.e(TAG, "receive: " + s);
            }
        });

        /**
         * 完整的写法
         */
        Observable.create(new Observable.OnSubscribe<Event1>() {
            @Override
            public void call(Subscriber<? super Event1> subscriber) {
                /**
                 * 发送一个数据
                 */
                subscriber.onNext(new Event1("event1"));
                subscriber.onCompleted(); //这个调用过之后，后面就不会收到事件了
                subscriber.onNext(new Event1("event2")); //这个是不会触发的
            }
        }).subscribe(new Subscriber<Event1>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError");
            }

            @Override
            public void onNext(Event1 event1) {
                Log.e(TAG, "onNext: " + event1.name);
            }
        });
    }

    /**
     * 测试线程调度器Scheduler
     */
    private void testScheduler() {
//        Schedulers.io(); //io线程，无上限，复用
//        Schedulers.computation(); //cpu密集型计算
//        Schedulers.newThread(); //新线程
//        Schedulers.immediate(); //不切换线程
//
//        /**
//         * 这个是RxAndroid的
//         */
//        AndroidSchedulers.mainThread(); //Android的UI线程

        /**
         * 测试
         */
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.e(TAG, "call in " + Thread.currentThread().getName());
                subscriber.onNext("hello");
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()) //指定事件发生的线程，在这里做完耗时任务
                .observeOn(Schedulers.computation()) //指定事件接收的线程，耗时任务搞完了，在这里进行下一步
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e(TAG, "receive " + s + " in " + Thread.currentThread().getName());
                    }
                });


        /**
         * 来个RxAndroid玩玩
         */
        Observable.create(new Observable.OnSubscribe<Event1>() {
            @Override
            public void call(Subscriber<? super Event1> subscriber) {
                Log.e(TAG, "call in: " + Thread.currentThread().getName());
                subscriber.onNext(new Event1("event1"));
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Event1>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Event1 event1) {
                        Log.e(TAG, "received in: " + Thread.currentThread().getName());
                        Log.e(TAG, "receive: " + event1.name);
                    }
                });


        /**
         * 测试一下runnable
         */
        Observable.just(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "now fuck in :" + Thread.currentThread().getName());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe(new Action1<Runnable>() {
                    @Override
                    public void call(Runnable runnable) {
                        Log.e(TAG, "now call in :" + Thread.currentThread().getName());
                        runnable.run();
                    }
                });
    }


    /**
     * 事件转换的API
     */
    private void testChangeEvent() {
        /**
         * 使用map(T,E)函数将T转换成E输出
         * 1对1的转换，
         */
        Observable.just(new Event1("hello"))
                .map(new Func1<Event1, String>() {
                    @Override
                    public String call(Event1 event1) {
                        return event1 != null ? event1.name : "null";
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String string) {
                        Log.e(TAG, "receive events:" + string);
                    }
                });

        Log.e(TAG, Line);

        /**
         * 将数组的每一个类型转换
         */
        Observable.from(new Event1[]{new Event1("event1"), new Event1("event2")})
                .map(new Func1<Event1, String>() {
                    @Override
                    public String call(Event1 event1) {
                        return event1 != null ? event1.name : "null";
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e(TAG, "receive event:" + s);
                    }
                });

        Log.e(TAG, Line);

        /**
         * 一个事件有多个成员进行转换
         */
        List<String> courses = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            courses.add("cources" + i);
        }
        Event1 event1 = new Event1("cEvent");
        event1.courses = courses;

        Observable.just(event1).flatMap(new Func1<Event1, Observable<String>>() {
            @Override
            public Observable<String> call(Event1 event1) {
                /**
                 * 核心在这里:这里将event展开，因为这个返回的是Observable，所以可以再次进行包装
                 * 等价于这个事件是两级的
                 */
                return Observable.from(event1.courses);
//                return Observable.just("shadiao");
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e(TAG, "receive event:" + s);
                    }
                });

        /**
         * 模仿上述写法，发现接收到的对象跟目标不同
         * 考虑一下如何实现转换?
         */
        Observable.just(event1).map(new Func1<Event1, List<String>>() {
            @Override
            public List<String> call(Event1 event1) {
                return event1.courses;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();


        /**
         * 不建议使用内置的lift方法，麻烦不好使
         */

        /**
         * 线程多次切换:observeOn()多次调用，它影响他后面紧跟的Subscriber
         * subscribeOn()多次调用，影响它前面最近的doOnSubscribe()
         */
        String tag = TAG + "SwitchSchedule";
        Observable.just(new Event1("event1"))
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        Log.e(tag, "doOnSubscribe1 in " + Thread.currentThread().getName());
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        Log.e(tag, "doOnSubscribe2 in " + Thread.currentThread().getName());
                    }
                })
                .subscribeOn(Schedulers.computation())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        Log.e(tag, "doOnSubscribe3 in " + Thread.currentThread().getName());
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .map(new Func1<Event1, String>() {
                    @Override
                    public String call(Event1 event1) {
                        Log.e(tag, "call in " + Thread.currentThread().getName());
                        return event1.name;
                    }
                }).observeOn(Schedulers.computation())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        Log.e(tag, "call in " + Thread.currentThread().getName());
                        return s + 10;
                    }
                }).observeOn(Schedulers.io())
                .map(new Func1<String, Event1>() {
                    @Override
                    public Event1 call(String s) {
                        Log.e(tag, "call in " + Thread.currentThread().getName());
                        return new Event1(s);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Event1>() {

                    @Override
                    public void onStart() {
                        //这个发生在事件订阅的线程
                        Log.e(tag, "onStart in " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onCompleted() {
                        Log.e(tag, "onCompleted in " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(tag, "onError in " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(Event1 event1) {
                        Log.e(tag, "onNext in " + Thread.currentThread().getName());
                        Log.e(tag, "receive event:" + event1.name);
                    }
                });
    }

    /**
     * 事件过滤、背压等其他API
     */
    private void testOtherAPI() {
        /**
         * throttleFirst(long,TimeUnit)
         * 在long时间内只有第一次事件才会被发送
         * TODO mmp没作用!!!
         */
        Observable.create(new Observable.OnSubscribe<Event1>() {
            @Override
            public void call(Subscriber<? super Event1> subscriber) {
                try {
                    while (true) {
                        Thread.sleep(200);
                        Event1 event = new Event1("" + System.currentTimeMillis());
                        Log.e(TAG, "send event:" + event.name);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Event1>() {
                    @Override
                    public void call(Event1 event1) {
                        Log.e(TAG, "receive event:" + event1.name);
                    }
                });
    }

    /**
     * 事件
     */
    private static class Event1 {
        String type = "";
        String name = "";

        List<String> courses = new ArrayList<>();

        public Event1() {
        }

        public Event1(String name) {
            this.name = name;
        }


        public static void printClass() {
            Log.e("Event1", Event1.class.getCanonicalName());
        }

        @Override
        public String toString() {
            return "Event1{" +
                    "type='" + type + '\'' +
                    ", name='" + name + '\'' +
                    ", courses=" + courses +
                    '}';
        }
    }


    /**
     * 测试代码
     */
    public static void testRecall() {
        Observable.create((Observable.OnSubscribe<Event1>) subscriber -> {
            Event1 event1 = new Event1();
            event1.name = "testRecall";
            subscriber.onNext(event1);
            subscriber.onCompleted();
        }).subscribe(new Observer<Event1>() {
            @Override
            public void onCompleted() {
                println(TAG + " - testRecall onCompleted ");
            }

            @Override
            public void onError(Throwable e) {
                println(TAG + " - testRecall onError " + e.getMessage());
            }

            @Override
            public void onNext(Event1 event1) {
                println(TAG + " - testRecall onNext " + event1);
            }
        });
    }

    /**
     * subscribe的时候就会触发观察者更新，所以要延后
     */
    private static void moniLiveData() {

        Event1 event = new Event1();

        Observable<Event1> observable = Observable.create(subscriber -> {
            event.name = String.valueOf(new Random().nextFloat());
            //通知
            subscriber.onNext(event);
        });

        Action1<Event1> observer = event1 -> {
            println("onChanged:" + event1.name);
        };

        //订阅
        observable.subscribe(observer);
    }

    public static void println(String content) {
        System.out.println(content);
    }
}
