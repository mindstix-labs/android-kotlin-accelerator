package com.mindstix.baseline

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * @author Apoorv Gupta
 */

class FloatingBubbleReceiver : BroadcastReceiver() {
    companion object {
        var hideBubbleFunction: (() -> Unit)? = null
        var stopBubbleFunction: (() -> Unit)? = null

        var isEnabled = true
    }

    override fun onReceive(
        context: Context,
        intent: Intent,
    ) {
        when (intent.action) {
            "ACTION_HIDE" -> {
                isEnabled = !isEnabled
                hideBubbleFunction?.invoke()
            }
            "ACTION_STOP" -> {
                stopBubbleFunction?.invoke()
            }
        }
    }
}
