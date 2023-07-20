package com.kingjinho.dontcallhim.usecase.add

import com.kingjinho.dontcallhim.repository.AddNumberRepo
import javax.inject.Inject

class AddNumberUseCaseImpl @Inject constructor(
    private val addNumberRepo: AddNumberRepo
) : AddNumberUseCase {
    override suspend fun invoke(number: String): Boolean {
        addNumberRepo.addNumber(number)
        return true
    }
}