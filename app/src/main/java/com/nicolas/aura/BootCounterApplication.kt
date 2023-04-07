package com.nicolas.aura

import android.app.Application
import com.nicolas.aura.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin

class BootCounterApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BootCounterApplication)
            workManagerFactory()
            modules(mainModule)
        }
    }

}