package com.kingjinho.dontcallhim.usecase

import com.kingjinho.dontcallhim.db.entity.PhoneNumber
import com.kingjinho.dontcallhim.repository.FakeFetchNumbersRepository
import com.kingjinho.dontcallhim.usecase.fetch.FetchNumbersUseCase
import kotlinx.coroutines.flow.Flow

class FakeFetchNumbersUseCaseImpl(private val repo: FakeFetchNumbersRepository): FetchNumbersUseCase {

    override fun invoke(): Flow<List<PhoneNumber>> {
        return repo.fetchPhoneNumbers()
    }
}