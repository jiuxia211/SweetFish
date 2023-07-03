package com.example.sweetfish.utils

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import com.smarteist.autoimageslider.SliderView

class BannerSliderView(context: Context, attrs: AttributeSet?) : SliderView(context, attrs) {
    private var startX: Float = 0f
    private var startY: Float = 0f
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val action = ev.action
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                startX = ev.x
                startY = ev.y
                parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = Math.abs(ev.x - startX)
                val dy = Math.abs(ev.y - startY)
                if (dx > dy) {
                    // 横向滑动，不拦截事件
                    parent.requestDisallowInterceptTouchEvent(true)
                } else {
                    // 纵向滑动，拦截事件，交给ViewPager2处理
                    parent.requestDisallowInterceptTouchEvent(false)
                    return false
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> parent.requestDisallowInterceptTouchEvent(
                false
            )
        }
        return super.onInterceptTouchEvent(ev)
    }
}