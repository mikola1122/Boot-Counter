package com.nicolas.aura.domain.useCase

import com.nicolas.aura.data.repository.MainRepository
import com.nicolas.aura.domain.mapper.BootDataMapper
import com.nicolas.aura.domain.model.BootEventModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetBootDataUseCase(
    private val repository: MainRepository,
    private val mapper: BootDataMapper
) {

    suspend operator fun invoke(): List<BootEventModel> = withContext(Dispatchers.IO) {
        mapper.mapFrom(repository.getBootDataList())
    }


}