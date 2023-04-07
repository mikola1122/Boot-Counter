package com.nicolas.aura.presentationHelpers

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.nicolas.aura.presentationHelpers.notifications.NotificationsFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.concurrent.TimeUnit

class RepeatableNotificationsWorker(context: Context, parameters: WorkerParameters) :
    CoroutineWorker(context, parameters),
    KoinComponent {

    private val notificationFactory: NotificationsFactory by inject()

    override suspend fun doWork(): Result {
        withContext(Dispatchers.IO) {
            notificationFactory.showNotification()
        }
        return Result.success()
    }

    companion object {

        fun selfScheduleRepeatable(context: Context) {
            val work =
                PeriodicWorkRequestBuilder<RepeatableNotificationsWorker>(15, TimeUnit.MINUTES)
                    .setConstraints(Constraints.NONE)
                    .build()
            WorkManager
                .getInstance(context)
                .enqueue(work)
        }
    }
}