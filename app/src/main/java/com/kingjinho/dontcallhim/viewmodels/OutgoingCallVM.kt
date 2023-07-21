package com.kingjinho.dontcallhim.viewmodels

import androidx.lifecycle.ViewModel
import com.kingjinho.dontcallhim.usecase.add.AddNumberUseCase
import com.kingjinho.dontcallhim.usecase.fetch.FetchNumbersUseCase
import com.kingjinho.dontcallhim.utils.isValidPhoneNumber
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OutgoingCallVM @Inject constructor(
    private val addNumberUseCase: AddNumberUseCase,
    private val fetchNumbersUseCase: FetchNumbersUseCase
) : ViewModel() {

    fun addNumber(number: String) = flow {
        if (!number.isValidPhoneNumber()) {
            emit(Result.Failure)
        } else {
            addNumberUseCase.invoke(number.replace("-", ""))
            emit(Result.Success)
        }
    }

    fun fetchSavedNumbers() = fetchNumbersUseCase.invoke()
}

sealed class Result {
    object Success : Result()
    object Failure : Result()
}