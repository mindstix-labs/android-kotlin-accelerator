package com.mindstix.floatingview.bubble

import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.LinearLayout
import com.mindstix.features.floatingview.R
import com.mindstix.floatingview.sez

internal class FloatingBottomBackground(context: Context) : Bubble(context, LinearLayout(context)) {

    internal var isShowing = false

    init {

        root = LayoutInflater.from(context).inflate(R.layout.bottom_background, null)

        setupLayoutParams()
    }

    override fun show() {
        if (isShowing) return
        isShowing = true
        super.show()
    }

    override fun remove() {
        isShowing = false
        super.remove()
    }

    private fun setupLayoutParams() {

        layoutParams.apply {
            width = WindowManager.LayoutParams.MATCH_PARENT
            height = sez.fullHeight / 5
            gravity = Gravity.BOTTOM or Gravity.CENTER
            flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                    WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH or
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION

//                windowAnimations = R.style.default_close_bubble_style

            format = PixelFormat.TRANSLUCENT
            type =
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY

        }

    }

}