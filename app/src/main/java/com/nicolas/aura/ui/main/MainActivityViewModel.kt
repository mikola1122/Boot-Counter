package com.nicolas.aura.ui.main

import androidx.lifecycle.viewModelScope
import com.nicolas.aura.domain.model.BootEventModel
import com.nicolas.aura.domain.useCase.GetBootDataUseCase
import com.nicolas.aura.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class MainActivityViewModel(
    getBootDataUseCase: GetBootDataUseCase
) : BaseViewModel<MainEvent, MainEffect>() {


    init {
        viewModelScope.launch {
            val useCase = getBootDataUseCase()

            when (val state = analizeBootDataList(useCase)) {
                is MainState.Loading -> {
                    MainEffect.ShowEmptyMessage.sendEffect()
                }
                is MainState.BootDataAvailableState -> {
                    MainEffect.ShowNewMessage(state.bootData).sendEffect()
                }
            }
        }
    }


    override fun handleEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OnCreateEvent -> {
            }
        }
    }

    private fun analizeBootDataList(bootData: List<BootEventModel>): MainState {
        return when (bootData.size) {
            0 -> {
                MainState.Loading
            }
            else -> {
                MainState.BootDataAvailableState(bootData.asString())
            }
        }

    }

    private fun List<BootEventModel>.asString(): String {
        val buffer = StringBuffer()
        this.forEachIndexed { index, bootEventModel ->
            buffer.append("$index - ${bootEventModel.timestamp},\n")
        }
        return buffer.toString()
    }

}