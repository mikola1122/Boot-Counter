package com.nicolas.aura.domain.mapper

import com.nicolas.aura.domain.model.BootEventModel

class BootDataMapper() {

    fun mapFrom(timestamps: List<Long>): List<BootEventModel> { // usually I use Kotlin Serilizer inside Mappers
        return timestamps.map { BootEventModel(timestamp = it) }
    }
}