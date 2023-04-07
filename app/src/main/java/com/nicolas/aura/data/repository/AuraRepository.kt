package com.nicolas.aura.data.repository

import com.nicolas.aura.data.db.DbSource
import com.nicolas.aura.data.db.model.BootEventsEntity
import com.nicolas.aura.data.utils.getCurrentTimestamp

class AuraRepository(
    val dbSource: DbSource
) : MainRepository {

    override suspend fun getBootDataList(): List<Long> {
        return dbSource.getAllBootEvents().map { it.timestamp }
    }

    override suspend fun addBootCompletedEvent() {
        return dbSource.addNewBootEvent(BootEventsEntity(timestamp = getCurrentTimestamp()))
    }

}