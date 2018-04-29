package com.juanpablofajardo.postsapp.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.juanpablofajardo.postsapp.R;

/**
 * Created by Juan Pablo Fajardo Cano on 4/29/18.
 */
public class CustomSwipeViewPager extends ViewPager {

    private boolean swipeable;

    public CustomSwipeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomSwipeViewPager);
        try {
            swipeable = a.getBoolean(R.styleable.CustomSwipeViewPager_swipeable, true);
        } finally {
            a.recycle();
        }
    }

    public void setSwipeable(boolean enabled){
        swipeable = enabled;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return swipeable && super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return swipeable && super.onTouchEvent(event);
    }

}
