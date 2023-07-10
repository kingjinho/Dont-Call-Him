package com.kingjinho.dontcallhim.usecase.fetch

import com.kingjinho.dontcallhim.repository.FetchNumbersRepo

class FetchNumbersUseCaseImpl(
    private val fetchNumbersRepo: FetchNumbersRepo
) : FetchNumbersUseCase {

    override fun invoke() = fetchNumbersRepo.fetchPhoneNumbers()

}