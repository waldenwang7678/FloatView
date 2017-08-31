package com.yonyou.floatviewtest;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by wangjt on 2017/8/30.
 */

public class ChildView extends View {
    public ChildView(Context context) {
        super(context);
    }

    public ChildView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ChildView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("asdasd", "child_ACTION_DOWN: " + event.getX());
                Log.d("asdasd", "child_ACTION_DOWN: " + event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("asdasd", "child_ACTION_MOVE: " + event.getX());
                Log.d("asdasd", "child_ACTION_MOVE: " + event.getY());
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                Log.d("asdasd", "child_ACTION_UP: " + event.getX());
                Log.d("asdasd", "child_ACTION_UP: " + event.getY());
        }
        return super.onTouchEvent(event);  //如果添加点击事件, true, 没有添加 false
        //false 只有 down 拦截
        // true 完全拦截, 动作全部执行
       // return super.onTouchEvent(event);
    }
}
