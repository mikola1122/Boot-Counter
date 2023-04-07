package com.nicolas.aura.ui.main

import com.nicolas.aura.ui.base.UiEffect
import com.nicolas.aura.ui.base.UiEvent

sealed interface MainState {
    object Loading : MainState
    data class BootDataAvailableState(val bootData: String): MainState
}

sealed class MainEvent : UiEvent {
    object OnCreateEvent: MainEvent()
}

sealed class MainEffect : UiEffect {
    object ShowEmptyMessage: MainEffect()
    data class ShowNewMessage(val newMassage: String): MainEffect()
}