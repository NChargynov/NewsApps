package com.example.newsapps.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class NonScrollViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {

    var isScrollable = false

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return isScrollable
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return isScrollable
    }
}