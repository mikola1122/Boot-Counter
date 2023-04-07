package com.nicolas.aura.presentationHelpers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootCompletedBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let { BootCompletedWorker.selfScheduleOnce(context = it) }
    }
}