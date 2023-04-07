package com.nicolas.aura.ui.main

import android.os.Bundle
import com.nicolas.aura.R
import com.nicolas.aura.databinding.ActivityMainBinding
import com.nicolas.aura.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainEvent, MainEffect, MainActivityViewModel>() {

    override val viewModel: MainActivityViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    override fun handleEffect(effect: MainEffect) {
        binding.messageTv.text = when (effect) {
            is MainEffect.ShowEmptyMessage -> {
                getString(R.string.main_screen_no_boots_detected)
            }
            is MainEffect.ShowNewMessage -> {
                 effect.newMassage
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}