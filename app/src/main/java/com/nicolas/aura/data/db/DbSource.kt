package com.nicolas.aura.data.db

import com.nicolas.aura.data.db.dao.BootEventsDao
import com.nicolas.aura.data.db.model.BootEventsEntity

class DbSource(private val dao: BootEventsDao) {

    suspend fun getAllBootEvents() = dao.getAllBootEvents()

    suspend fun addNewBootEvent(bootEvent: BootEventsEntity) = dao.insertOrIgnoreBootEvent(bootEvent)

}