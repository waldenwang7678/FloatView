package com.yonyou.floatviewtest;

import android.content.Context;
import android.graphics.PixelFormat;
import android.support.annotation.LayoutRes;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * Created by wangjt on 2017/8/30.
 */

public class FloatView extends FrameLayout {

    private Context mContext;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mWmParams;
    boolean isShow = false;

    public FloatView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);

        DisplayMetrics dm = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(dm);
        int mScreenWidth = dm.widthPixels;
        int mScreenHeight = dm.heightPixels;

        mWmParams = new WindowManager.LayoutParams();
        mWmParams.x = mScreenWidth / 4;
        mWmParams.y = mScreenHeight / 4;
        mWmParams.gravity = Gravity.START | Gravity.TOP;
        mWmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        mWmParams.width = FrameLayout.LayoutParams.WRAP_CONTENT;
        mWmParams.height = FrameLayout.LayoutParams.WRAP_CONTENT;
        mWmParams.format = PixelFormat.RGBA_8888;
    }

    public void addFloatView(View view) {
        this.addView(view);
    }

    public void addFloatView(@LayoutRes int viewId) {
        View.inflate(mContext, viewId, this);
    }

    public void showView() {
        if (isShow) return;
        mWindowManager.addView(this, mWmParams);
        isShow = true;
    }

    public void hideView() {
        try {
            if (!isShow) return;
            mWindowManager.removeView(this);
            isShow = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    float startX;
    float startY;
    boolean touchDown = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:  //父控件事件分发给子控件,
                touchDown = true;
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (touchDown) {
                    mWmParams.x = (int) (event.getRawX() - startX);
                    mWmParams.y = (int) (event.getRawY() - startY);
                } else {
                    mWmParams.x = (int) (event.getRawX() - x);
                    mWmParams.y = (int) (event.getRawY() - y);
                }
                mWindowManager.updateViewLayout(this, mWmParams);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                startX = 0;
                startY = 0;
                touchDown = false;
                break;
        }
        return true;
    }

    boolean isIntercept = false;
    float x;
    float y;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = ev.getX();
                y = ev.getY();
                isIntercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
                isIntercept = Math.abs(ev.getX() - x) > 2 || Math.abs(ev.getY() - y) > 2;
                Log.d("asdasd", "onInterceptTouchEvent_mWmParams.x: " + mWmParams.x);
                Log.d("asdasd", "onInterceptTouchEvent_mWmParams.y: " + mWmParams.y);

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isIntercept = false;
                break;
        }
        return isIntercept;
    }


}
