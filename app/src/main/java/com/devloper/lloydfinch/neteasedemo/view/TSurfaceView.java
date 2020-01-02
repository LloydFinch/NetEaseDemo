package com.devloper.lloydfinch.neteasedemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

/**
 * Name: TSurfaceView
 * Author: lloydfinch
 * Function: SurfaceView
 * Date: 2020-01-02 15:44
 * Modify: lloydfinch 2020-01-02 15:44
 */
public class TSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private static final String TAG = "TSurfaceView";
    private static int DRAW_INTERVAL = 200;

    private SurfaceHolder mSurfaceHolder;
    private Canvas mCanvas;
    private boolean isActive;

    private Thread mDrawThread;
    private Random random;

    public TSurfaceView(Context context) {
        this(context, null);
    }

    public TSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "surfaceCreated: ");
        isActive = true;
        random = new Random();
        mDrawThread = new Thread(this);
        mDrawThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d(TAG, "surfaceChanged: ");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "surfaceDestroyed: ");
        isActive = false;
        /**
         * TODO 其他释放操作
         */
    }

    @Override
    public void run() {
        Log.d(TAG, "run: ");
        while (isActive) {
            draw();
            try {
                Thread.sleep(DRAW_INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void draw() {
        try {
            mCanvas = mSurfaceHolder.lockCanvas();
            /**
             * 进行绘制
             */
            mCanvas.drawColor(Color.argb(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mCanvas != null) {
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}
