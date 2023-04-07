package com.nicolas.aura.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nicolas.aura.data.db.dao.BootEventsDao
import com.nicolas.aura.data.db.model.BootEventsEntity

@Database(entities = [BootEventsEntity::class], version = 1)
abstract class AuraDatabase : RoomDatabase() {
    abstract fun bootEventsDao(): BootEventsDao

    companion object {

        private const val databaseName = "aura_database"

        private lateinit var db: AuraDatabase

        fun getInstance(context: Context): AuraDatabase {
            db = Room.databaseBuilder(context, AuraDatabase::class.java, databaseName)
                .build()
            return db
        }
    }
}