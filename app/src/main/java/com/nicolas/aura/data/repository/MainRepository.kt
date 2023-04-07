package com.nicolas.aura.data.repository

interface MainRepository {

    suspend fun getBootDataList(): List<Long>

    suspend fun addBootCompletedEvent()
}