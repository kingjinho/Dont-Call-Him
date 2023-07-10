package com.kingjinho.dontcallhim.usecase.add

import com.kingjinho.dontcallhim.repository.AddNumberRepo

class AddNumberUseCaseImpl(
    private val addNumberRepo: AddNumberRepo
) : AddNumberUseCase {
    override suspend fun invoke(number: String): Boolean {
        addNumberRepo.addNumber(number)
        return true
    }
}