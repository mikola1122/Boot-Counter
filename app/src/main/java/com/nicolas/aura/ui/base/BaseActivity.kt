package com.nicolas.aura.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

abstract class BaseActivity<
        Event : UiEvent,
        Effect : UiEffect,
        ViewModel : BaseViewModel<Event, Effect>> :
    AppCompatActivity() {

    protected abstract val viewModel: ViewModel

    protected abstract fun handleEffect(effect: Effect)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeOnEffect()
    }

    private fun subscribeOnEffect() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.effect.collect {
                    handleEffect(it)
                }
            }
        }
    }
}