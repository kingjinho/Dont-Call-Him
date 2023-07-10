package com.kingjinho.dontcallhim.usecase.add

interface AddNumberUseCase {
    suspend operator fun invoke(number: String): Boolean
}