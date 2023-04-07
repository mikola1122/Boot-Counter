package com.nicolas.aura.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bootEvents")
data class BootEventsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val timestamp: Long
) {
}