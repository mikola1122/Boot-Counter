package com.nicolas.aura.domain.useCase

import com.nicolas.aura.data.repository.MainRepository
import com.nicolas.aura.domain.mapper.BootDataMapper
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

class GetBootDataUseCase (
    private val repository: MainRepository,
    private val mapper: BootDataMapper
) {

    operator fun invoke() = repository.getBootDataFlow()
        .filterNotNull()
        .map {
            mapper.mapFrom()
        }
}