package com.nicolas.aura.ui.main

import androidx.lifecycle.viewModelScope
import com.nicolas.aura.domain.useCase.GetBootDataUseCase
import com.nicolas.aura.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class MainActivityViewModel(
    getBootDataUseCase: GetBootDataUseCase
) : BaseViewModel<MainEvent, MainEffect>() {


    init {
        getBootDataUseCase()
            .map(MainState::BootDataAvailableState)
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                MainState.Loading
            )
            .onStart {
                MainEffect.ShowEmptyMessage.sendEffect()
            }
            .flowOn(Dispatchers.IO)
            .onEach { data ->
                when(data){
                    is MainState.Loading -> {
                        MainEffect.ShowEmptyMessage.sendEffect()
                    }
                    is MainState.BootDataAvailableState -> {
                        MainEffect.ShowNewMessage(data.bootData.toString()).sendEffect()
                    }
                }
            }
            .launchIn(viewModelScope)
    }



    override fun handleEvent(event: MainEvent) {
        when(event) {
            is MainEvent.OnCreateEvent -> {
            }
        }
    }

}