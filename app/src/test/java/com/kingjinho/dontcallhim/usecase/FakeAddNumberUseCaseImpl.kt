package com.kingjinho.dontcallhim.usecase

import com.kingjinho.dontcallhim.repository.FakeAddNumberRepository
import com.kingjinho.dontcallhim.usecase.add.AddNumberUseCase

class FakeAddNumberUseCaseImpl(private val repo: FakeAddNumberRepository): AddNumberUseCase {

    override suspend fun invoke(number: String): Boolean {
        repo.addNumber(number)
        return true
    }

    override suspend fun isNumberAlreadySaved(number: String): Boolean {
        return repo.numberExists(number)
    }
}