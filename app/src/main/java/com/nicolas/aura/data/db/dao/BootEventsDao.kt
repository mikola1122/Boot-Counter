package com.nicolas.aura.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nicolas.aura.data.db.Table
import com.nicolas.aura.data.db.model.BootEventsEntity

@Dao
abstract class BootEventsDao {

    @Query("SELECT * FROM ${Table.BOOT_EVENTS}")
    abstract suspend fun getAllBootEvents(): List<BootEventsEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertOrIgnoreBootEvent(entity: BootEventsEntity)

    @Query("DELETE FROM ${Table.BOOT_EVENTS}") //never forget to clean up after yourself;-)
    abstract suspend fun clearTable()

}