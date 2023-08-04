package com.kingjinho.dontcallhim.usecase.fetch

import com.kingjinho.dontcallhim.db.entity.PhoneNumber
import kotlinx.coroutines.flow.Flow

interface FetchNumbersUseCase {
    operator fun invoke(): Flow<List<PhoneNumber>>
}