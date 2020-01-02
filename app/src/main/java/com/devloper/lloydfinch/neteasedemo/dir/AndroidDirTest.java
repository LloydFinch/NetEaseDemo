package com.devloper.lloydfinch.neteasedemo.dir;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Name: AndroidDirTest
 * Author: lloydfinch
 * Function: Obtain Android all dir Path
 * Date: 2019-08-08 14:34
 * Modify: lloydfinch 2019-08-08 14:34
 */
public class AndroidDirTest {

    private static final String TAG = "AndroidDirTest";

    public void test(Context context) {
        listAllDirs(context);
    }

    /**
     * 列举所有目录
     *
     * @param context
     */
    private void listAllDirs(Context context) {
        //==========针对app的======================================================
        /**
         * 针对内部存储空间
         * /data/data/package/files
         */
        File filesDir = context.getFilesDir();
        if (filesDir != null) {
            Log.e(TAG, "filesDir: " + filesDir.getAbsolutePath());
        }

        /**
         * 针对内部存储空间
         * /data/data/package/cache
         */
        File cacheDir = context.getCacheDir();
        if (cacheDir != null) {
            Log.e(TAG, "cacheDir: " + cacheDir.getAbsolutePath());
        }

        /**
         * 针对内部存储空间
         * /data/data/package
         */
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            File dataDir = context.getDataDir();
            if (dataDir != null) {
                Log.e(TAG, "dataDir: " + dataDir);
            }
        }
        //针对内部存储空间的一般不建议使用

        /**
         * 针对外部存储空间 会生成一个传入的String的目录
         * /sdcard/Android/data/package/files/fuck
         */
//        File externalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File externalFilesDir = context.getExternalFilesDir("fuck");
        if (externalFilesDir != null) {
            Log.e(TAG, "externalFilesDir(Music): " + externalFilesDir);
        }

        /**
         * 针对外部存储空间
         * /sdcard/Android/data/package/cache
         */
        File externalCacheDir = context.getExternalCacheDir();
        if (externalCacheDir != null) {
            Log.e(TAG, "externalCacheDir: " + externalCacheDir);
        }
        //==========针对app的======================================================


        Log.e(TAG, "===============================================================");
        //==========app无关的======================================================
        /**
         * /system
         */
        File rootDirectory = Environment.getRootDirectory();
        if (rootDirectory != null) {
            Log.e(TAG, "rootDirectory: " + rootDirectory);
        }

        /**
         * /data
         */
        File dataDirectory = Environment.getDataDirectory();
        if (dataDirectory != null) {
            Log.e(TAG, "dataDirectory: " + dataDirectory);
        }

        /**
         * /data/cache
         */
        File downloadCacheDirectory = Environment.getDownloadCacheDirectory();
        if (downloadCacheDirectory != null) {
            Log.e(TAG, "downloadCacheDirectory: " + downloadCacheDirectory);
        }

        /**
         * /sdcard
         */
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory != null) {
            Log.e(TAG, "externalStorageDirectory: " + externalStorageDirectory);
        }

        /**
         * /sdcard/hello
         */
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory("hello");
        if (externalStoragePublicDirectory != null) {
            Log.e(TAG, "externalStoragePublicDirectory: " + externalStoragePublicDirectory);
        }
    }
}
