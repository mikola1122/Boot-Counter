package com.nicolas.aura.data.repository

import com.nicolas.aura.data.db.MainDbSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuraRepository(
    val dbSource: MainDbSource
): MainRepository {

    override fun getBootDataFlow(): StateFlow<Long> {
        // TODO: update repo
        return MutableStateFlow(-1L)
    }

    override fun addBootCompletedEvent(): Boolean {
        // TODO: store this event into DB
        return true
    }

}