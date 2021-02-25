package com.devloper.lloydfinch.neteasedemo.okhttp;

import android.Manifest;
import android.app.Activity;
import android.os.Environment;
import android.widget.Toast;

import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpDemo {

    private static final String TAG = "OkHttpDemo";

    public void test() {
        testGeneralGet();
//        testGeneralPost();
//        testInterceptor();
    }

    /**
     * 一般的get
     */
    private void testGeneralGet() {
        String url = "http://www.baidu.com";
        Log.e(TAG, "url=" + url);
        OkHttpClient client = new OkHttpClient();
        //一大堆初始化

        Request request = new Request.Builder().url(url).get().build();
        //初始化

        Call call = client.newCall(request);

//        call.execute();

        //RealCall, Transmitter

//        call.cancel();


        //同步
        // try {
        //     client.dispatcher().executed(this); //添加到队列
        //     return getResponseWithInterceptorChain(); //返回结果
        // } finally {
        //     client.dispatcher().finished(this); //移除队列，promoteAndExecute()
        // }

        //异步
        //1 入队       readyAsyncCalls.add(call); //准备队列
        //2      promoteAndExecute();
        //        Response response = getResponseWithInterceptorChain();
        //client.dispatcher().finished(this);

        //入队即可
        //入参是url，"GET"
        //出参是Response(ResponseBody(Source))
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String message = response.message();
                ResponseBody body = response.body();
                assert body != null;
                String content = body.string();
                Log.e(TAG, String.format("message:%s,content:%s", message, content));
            }
        });

//        /**
//         * 或者直接获取response，从而进行特殊处理
//         */
//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    //阻塞式调用
//                    Log.e(TAG, "run before1...");
//                    Log.e(TAG, "run before2...");
//        Response response = call.execute();
//                    Log.e(TAG, "run after1..." + responsse.message());
//                    Log.e(TAG, "run after2..." + response.message());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
    }

    /**
     * 测试post（一般的字符串提交）
     */
    private void testGeneralPost() {
        String url = "https://api.github.com/markdown/raw";
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), "fuck");
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure:" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) {
                Log.e(TAG, response.message());
                ResponseBody body = response.body();
                if (body != null) {
                    try {
                        Log.e(TAG, "body =" + body.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 上传文件
     * 没什么不同的，就是变一下requestBody的事
     */
    public void testFileUpload(Activity activity) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(aBoolean -> {
            if (aBoolean) {
                String url = "https://api.github.com/markdown/raw";
                OkHttpClient client = new OkHttpClient();
                File path = Environment.getExternalStorageDirectory();
                File file = new File(path, "test.txt");
                if (!file.exists()) {
                    try {
                        boolean newFile = file.createNewFile();
                        Log.e(TAG, "file not exists, so create a new file " + newFile);
                    } catch (IOException e) {
                        Log.e(TAG, "create file error: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
                MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
                RequestBody requestBody = RequestBody.create(mediaType, file);
                Call call = client.newCall(new Request.Builder().url(url).put(requestBody).build());
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e(TAG, "error: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) {
                        Log.e(TAG, "onResponse:" + response.message());
                        ResponseBody body = response.body();
                        if (body != null) {
                            try {
                                Log.e(TAG, "body = " + body.string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            } else {
                Toast.makeText(activity, "no files permission", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 拦截器相关(核心思想)
     */
    private void testInterceptor() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                long time = System.nanoTime();

                Log.e(TAG, "intercept: " + Thread.currentThread().getName());
                Request request = chain.request();
                Log.e(TAG, "intercept request:" + request.url().toString());
                Response response = chain.proceed(request);
                Log.e(TAG, "intercept response:" + response.message());
                Log.e(TAG, "spend time: " + ((System.nanoTime() - time) / 1e6d));
                return response;
            }
        }).build();
        String url = "http://www.baodu.com";
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "test Interceptor error:" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "test Interceptor response:" + response.message());
            }
        });
    }

    /**
     * 用来跟源码，最基础的一般的get请求
     */
    private void testSourceCode() {
        String url = "http://www.baidu.com";
        //创建OkHttpClient，内部会通过创建一个默认的Builder来初始化一些属性，
        OkHttpClient client = new OkHttpClient();
        //默认请求方式为GET，Builder模式来创建一个Request
        Request request = new Request.Builder().url(url).build();
        //创建一个Call对象
        Call call = client.newCall(request);
        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        };
        call.enqueue(callback);
    }


    public static class Log {
        public static void e(String tag, String msg) {
            System.out.println(tag + ": " + msg);
        }
    }
}
