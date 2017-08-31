package com.yonyou.floatviewtest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by wangjt on 2017/8/30.
 */

public class ParentView extends RelativeLayout {
    public ParentView(Context context) {
        super(context);
    }

    public ParentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: //父控件不拦截事件时 , onTouchEvent.ACTINDOWN 不被执行
                Log.d("asdasd", "parent_ACTION_DOWN: " + event.getX());
                Log.d("asdasd", "parent_ACTION_DOWN: " + event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("asdasd", "parent_ACTION_MOVE: " + event.getX());
                Log.d("asdasd", "parent_ACTION_MOVE: " + event.getY());


                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                Log.d("asdasd", "parent_ACTION_UP: " + event.getX());
                Log.d("asdasd", "parent_ACTION_UP: " + event.getY());
        }
        return super.onTouchEvent(event);
    }

    boolean isIntercept = false;
    float x;
    float y;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("asdasd", "parent_onInterceptTouchEvent_ACTION_DOWN: " + event.getX());
                Log.d("asdasd", "parent_onInterceptTouchEvent_ACTION_DOWN: " + event.getY());
                x = event.getX();
                y = event.getY();
                isIntercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("asdasd", "parent_onInterceptTouchEvent_ACTION_MOVE: " + event.getX());
                Log.d("asdasd", "parent_onInterceptTouchEvent_ACTION_MOVE: " + event.getY());
                if (Math.abs(event.getX() - x) > 10 || Math.abs(event.getY() - y) > 10) {
                    isIntercept = true;
                } else {
                    isIntercept = false;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                Log.d("asdasd", "parent_onInterceptTouchEvent_ACTION_UP: " + event.getX());
                Log.d("asdasd", "parent_onInterceptTouchEvent_ACTION_UP: " + event.getY());
                isIntercept = false;
                break;
        }
        return isIntercept;
    }
}
