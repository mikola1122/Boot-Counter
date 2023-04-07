package com.nicolas.aura.presentationHelpers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.nicolas.aura.data.repository.MainRepository
import com.nicolas.aura.presentationHelpers.notifications.NotificationsFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BootCompletedWorker(val context: Context, parameters: WorkerParameters) :
    CoroutineWorker(context, parameters),
    KoinComponent {

    private val notificationFactory: NotificationsFactory by inject()
    private val mainRepository: MainRepository by inject()

    override suspend fun doWork(): Result {
        withContext(Dispatchers.IO) {
            mainRepository.addBootCompletedEvent()
            notificationFactory.showNotification()
            RepeatableNotificationsWorker.selfScheduleRepeatable(context) //AlarmManager would be more accurate with time, but will use more battery power
        }
        return Result.success()
    }

    companion object {

        fun selfScheduleOnce(context: Context) {
            WorkManager
                .getInstance(context)
                .beginUniqueWork(
                    "BootCompleted",
                    ExistingWorkPolicy.REPLACE,
                    OneTimeWorkRequestBuilder<BootCompletedWorker>()
                        .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                        .build()
                )
                .enqueue()
        }
    }
}