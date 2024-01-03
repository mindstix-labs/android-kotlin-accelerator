/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */
package com.mindstix.baseline.ui.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.mindstix.baseline.R
import com.mindstix.baseline.ui.MainActivity
import com.mindstix.core.logger.Logger
import java.security.SecureRandom

/**
 * AppFirebaseMessagingService to handle FCM notifications received
 *
 * @author Asim Shah
 */
class AppFirebaseMessagingService : FirebaseMessagingService() {
    private val mTAG = javaClass.simpleName

    override fun onNewToken(newtoken: String) {
        super.onNewToken(newtoken)
        Logger.d { "FCM Token Generated -> $newtoken" }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val notificationTitle = remoteMessage.notification?.title
        val notificationBody = remoteMessage.notification?.body
        Logger.d(mTAG, "Notification Title: $notificationTitle" + "\n" +
                "Notification Body: $notificationBody")

        val notificationIntent = Intent(this, MainActivity::class.java)
        notificationIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        notificationIntent.action = Intent.ACTION_VIEW
        notificationIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP

        val pendingIntent = PendingIntent.getActivity(
            this,
            System.currentTimeMillis().toInt(),
            notificationIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
        )

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationBuilder = Notification.Builder(this)
            .setContentTitle(notificationTitle)
            .setContentText(notificationBody)
            .setSmallIcon(R.mipmap.mindstix_icon_round)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.mindstix_icon_round))
            .setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        val channelId = getString(R.string.default_notification_channel_id)
        val channel = NotificationChannel(
            channelId,
            notificationTitle,
            NotificationManager.IMPORTANCE_DEFAULT,
        )
        channel.description = notificationBody
        notificationManager.createNotificationChannel(channel)
        notificationBuilder.setChannelId(channelId)

        val random = SecureRandom()
        notificationManager.notify(random.nextInt(), notificationBuilder.build())
    }
}