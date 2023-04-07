package com.nicolas.aura.presentationHelpers.notifications

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.nicolas.aura.R
import com.nicolas.aura.data.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

private const val SyncNotificationId = 0
private const val SyncNotificationChannelID = "SyncNotificationChannel"

class NotificationsFactory(
    private val context: Context,
    private val mainRepository: MainRepository
) : CoroutineScope {

    override val coroutineContext
        get() = Dispatchers.IO + Job()

    fun showNotification() {
        // TODO: chose message depend on repository data
        val message: String =
            context.getString(R.string.boot_completed_notification_message_no_boots_detected)

        val notification = NotificationCompat.Builder(
            context,
            SyncNotificationChannelID,
        )
            .setSmallIcon(
                R.drawable.ic_launcher_background,
            )
            .setContentTitle(context.getString(R.string.boot_completed_notification_title))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentText(message)
            .build()

        notify(SyncNotificationId, notification)
    }

    private fun notify(notificationId: Int, notification: Notification) {
        with(NotificationManagerCompat.from(context)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notificationChannel = NotificationChannel(
                    SyncNotificationChannelID,
                    context.getString(R.string.boot_completed_notification_channel_name),
                    NotificationManager.IMPORTANCE_HIGH
                )
                notificationChannel.description =
                    context.getString(R.string.boot_completed_notification_channel_description)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.WHITE
                notificationChannel.enableVibration(true)
                createNotificationChannel(notificationChannel)
            }
        if (checkNotificationPermission()) return

        notify(notificationId, notification)
        }
    }

    private fun checkNotificationPermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val isNotificationPermissionGranted = ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
            if (!isNotificationPermissionGranted) {
                return true
            }
        }
        return false
    }


}