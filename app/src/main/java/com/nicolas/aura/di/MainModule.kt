package com.nicolas.aura.di

import com.nicolas.aura.data.db.DbSource
import com.nicolas.aura.data.db.MainDbSource
import com.nicolas.aura.data.repository.AuraRepository
import com.nicolas.aura.data.repository.MainRepository
import com.nicolas.aura.domain.mapper.BootDataMapper
import com.nicolas.aura.domain.useCase.GetBootDataUseCase
import com.nicolas.aura.ui.main.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    viewModel {
        MainActivityViewModel(
            getBootDataUseCase = get()
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

    single<MainDbSource> {
        DbSource()
    }

}