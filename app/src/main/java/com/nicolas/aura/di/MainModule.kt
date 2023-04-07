package com.nicolas.aura.di

import androidx.work.WorkerParameters
import com.nicolas.aura.data.db.AuraDatabase
import com.nicolas.aura.data.db.DbSource
import com.nicolas.aura.data.repository.AuraRepository
import com.nicolas.aura.data.repository.MainRepository
import com.nicolas.aura.domain.mapper.BootDataMapper
import com.nicolas.aura.domain.useCase.GetBootDataUseCase
import com.nicolas.aura.presentationHelpers.BootCompletedWorker
import com.nicolas.aura.presentationHelpers.RepeatableNotificationsWorker
import com.nicolas.aura.presentationHelpers.notifications.NotificationsFactory
import com.nicolas.aura.ui.main.MainActivityViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

val mainModule = module {

    viewModel {
        MainActivityViewModel(
            getBootDataUseCase = get()
        )
    }

    worker { (workerParams: WorkerParameters) ->
        BootCompletedWorker(
            context = androidContext(),
            parameters = workerParams
        )
    }

    worker { (workerParams: WorkerParameters) ->
        RepeatableNotificationsWorker(
            context = androidContext(),
            parameters = workerParams
        )
    }

    single<MainRepository> {
        AuraRepository(
            dbSource = get()
        )
    }

    single {
        GetBootDataUseCase(
            repository = get(),
            mapper = get()
        )
    }

    single {
        BootDataMapper()
    }

    single {
        DbSource(
            dao = get<AuraDatabase>().bootEventsDao()
        )
    }

    single {
        NotificationsFactory(
            context = androidContext(),
            mainRepository = get()
        )
    }

    single { AuraDatabase.getInstance(get()) }

}