package com.kingjinho.dontcallhim.usecase.fetch

import com.kingjinho.dontcallhim.repository.FetchNumbersRepo
import javax.inject.Inject

class FetchNumbersUseCaseImpl @Inject constructor(
    private val fetchNumbersRepo: FetchNumbersRepo
) : FetchNumbersUseCase {

    override fun invoke() = fetchNumbersRepo.fetchPhoneNumbers()

}