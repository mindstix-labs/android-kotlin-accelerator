package com.mindstix.floatingview.helper

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.mindstix.features.floatingview.R

class NotificationHelper
@JvmOverloads
constructor(
    private val context: Context,
    private val channelId: String = "bubble_service",
    private val channelName: String = "floating bubble",
    val notificationId: Int = 101,
) {
    /**
     * update notification if already exists
     * */
    @SuppressLint("MissingPermission")
    fun notify(notification: Notification) {
        NotificationManagerCompat.from(context).notify(notificationId, notification)
    }

    /**
     * create notification channel on android 8 and above
     * */
    fun createNotificationChannel() {
        createNotificationChannel(channelId, channelName)
    }

    /**
     * Default notification for FloatingBubbleService foreground service.
     *
     * In case you don't have time :)
     * */
    fun defaultNotification(): Notification =
        NotificationCompat.Builder(context, channelId)
            .setOngoing(true)
            .setSmallIcon(R.drawable.ic_rounded_blue_diamond)
            .setContentTitle("ShopMate is running")
            .setPriority(NotificationCompat.PRIORITY_MIN)
            .setCategory(Notification.CATEGORY_SERVICE)
            .setSilent(true)
            .build()

    private fun createNotificationChannel(
        channelId: String,
        channelName: String,
    ) {
        val channel =
            NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT, // IMPORTANCE_NONE recreate the notification if update
            ).apply {
                lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            }
        NotificationManagerCompat.from(context).createNotificationChannel(channel)
    }
}
