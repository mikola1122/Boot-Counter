package com.nicolas.aura.domain.mapper

import com.nicolas.aura.domain.model.BootEventModel

class BootDataMapper() {

    fun mapFrom(timestamps: List<Long>): List<BootEventModel> {
        return timestamps.map { BootEventModel(timestamp = it) }
    }
}