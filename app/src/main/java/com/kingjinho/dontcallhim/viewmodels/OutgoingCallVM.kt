package com.kingjinho.dontcallhim.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kingjinho.dontcallhim.usecase.add.AddNumberUseCase
import com.kingjinho.dontcallhim.usecase.fetch.FetchNumbersUseCase
import com.kingjinho.dontcallhim.utils.isValidPhoneNumber
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Provider

class OutgoingCallVMFactory @Inject constructor(
    private val addNumberUseCaseProvider: Provider<AddNumberUseCase>,
    private val fetchNumbersUseCaseProvider: Provider<FetchNumbersUseCase>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return if (modelClass.isAssignableFrom(OutgoingCallVM::class.java)) {
            OutgoingCallVM(
                addNumberUseCase = addNumberUseCaseProvider.get(),
                fetchNumbersUseCase = fetchNumbersUseCaseProvider.get()
            ) as T
        } else {
            throw IllegalArgumentException("modelClass is not assignable: ${modelClass.canonicalName}")
        }
    }

}

class OutgoingCallVM(
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