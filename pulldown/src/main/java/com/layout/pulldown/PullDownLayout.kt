package com.layout.pulldown

import android.annotation.SuppressLint
import android.content.Context
import android.support.annotation.NonNull
import android.support.v4.widget.ViewDragHelper
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.widget.FrameLayout

/**
 * Created by deepan-5901 on 30/01/18.
 */
class PullDownLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    private val dragger = ViewDragHelper.create(this, 1f / 8f, ViewDragCallback())
    private val minimumFlingVelocity = ViewConfiguration.get(context).scaledMinimumFlingVelocity
    private var callback: Callback? = null

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean = if (ev.pointerCount > 1 || !isEnabled) false else dragger.shouldInterceptTouchEvent(ev)

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(@NonNull event: MotionEvent): Boolean = if (event.pointerCount > 1 || !isEnabled) false
    else {
        try {
            dragger.processTouchEvent(event)
        } catch (e: Exception) {
        }
        true
    }

    override fun computeScroll() {
        if (dragger.continueSettling(true)) ViewCompat.postInvalidateOnAnimation(this)
    }

    interface Callback {
        fun onPullStart()
        fun onPull(progress: Float)
        fun onPullCancel()
        fun onPullComplete()
    }

    private inner class ViewDragCallback : ViewDragHelper.Callback() {

        override fun tryCaptureView(child: View, pointerId: Int): Boolean = true

        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int = 0

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int = Math.max(0, top)

        override fun getViewHorizontalDragRange(child: View): Int = 0

        override fun getViewVerticalDragRange(child: View): Int = height

        override fun onViewCaptured(capturedChild: View, activePointerId: Int) {
            callback?.onPullStart()
        }

        override fun onViewPositionChanged(changedView: View, left: Int, top: Int, dx: Int, dy: Int) {
            callback?.onPull(top.toFloat() / height.toFloat())
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            val threshold = if (yvel > minimumFlingVelocity) height / 6 else height / 3
            if (releasedChild.top > threshold) callback?.onPullComplete()
            else {
                callback?.onPullCancel()
                dragger.settleCapturedViewAt(0, 0)
                invalidate()
            }
        }
    }
}