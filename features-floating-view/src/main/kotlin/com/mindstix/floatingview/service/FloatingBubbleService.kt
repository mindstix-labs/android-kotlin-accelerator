package com.mindstix.floatingview.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.mindstix.floatingview.canDrawOverlays
import com.mindstix.floatingview.helper.NotificationHelper
import com.mindstix.floatingview.sez


abstract class FloatingBubbleService : Service() {

    open fun startNotificationForeground() {
        val noti = NotificationHelper(this)
        noti.createNotificationChannel()
        startForeground(noti.notificationId, noti.defaultNotification())
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()

        if (canDrawOverlays().not()) {
            throw SecurityException("Permission Denied: \"display over other app\" permission IS NOT granted!")
        }

        sez.with(this.applicationContext)

        startNotificationForeground()
        setup()
    }

    abstract fun setup()

    abstract fun removeAll()

    override fun onDestroy() {
        removeAll()
        super.onDestroy()
    }
}