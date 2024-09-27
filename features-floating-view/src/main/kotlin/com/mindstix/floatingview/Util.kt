package com.mindstix.floatingview

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Rect
import android.provider.Settings
import android.view.View
import android.view.ViewTreeObserver
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.torrydo.screenez.ScreenEasy
import java.lang.ref.WeakReference
import kotlin.math.pow
import kotlin.math.sqrt

internal fun Int.toBitmap(context: Context): Bitmap? {
    return WeakReference(context).get()?.let { weakContext ->
        ContextCompat.getDrawable(weakContext, this)!!.toBitmap()
    }
}

internal fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

internal fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

internal val sez = ScreenEasy()

// exclude view gesture on home screen -------------------------------------------------------------
private var exclusionRects: MutableList<Rect> = ArrayList()

internal fun View.updateGestureExclusion() {
    val screenSize = sez.fullSize

    exclusionRects.clear()

    val rect = Rect(0, 0, this.width, screenSize.height)
    exclusionRects.add(rect)

    this.systemGestureExclusionRects = exclusionRects
}

// permission --------------------------------------------------------------------------------------

/**
 * by default, display over other app permission will be granted automatically if android's version smaller than android M
 *
 * - some MIUI devices may not work properly
 *
 * */
internal fun Context.canDrawOverlays(): Boolean {
    return Settings.canDrawOverlays(this)
}

inline fun View.afterMeasured(crossinline afterMeasuredWork: () -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(
        object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                if (measuredWidth > 0 && measuredHeight > 0) {
                    viewTreeObserver.removeOnGlobalLayoutListener(this)
                    afterMeasuredWork()
                }
            }
        },
    )
}

/**
 * @return Point( 0 .. x, 0 .. y )
 * */
internal fun View.getXYOnScreen(): Pair<Int, Int> {
    val arr = IntArray(2)
    this.getLocationOnScreen(arr)

    return Pair(arr[0], arr[1])
}

internal object XMath {
    fun distance(
        x1: Double,
        y1: Double,
        x2: Double,
        y2: Double,
    ): Double {
        return sqrt((x2 - x1).pow(2) + (y2 - y1).pow(2))
    }
}
