package com.nicolas.aura.data.repository

import kotlinx.coroutines.flow.StateFlow

interface MainRepository {

    fun getBootDataFlow(): StateFlow<Long>
}