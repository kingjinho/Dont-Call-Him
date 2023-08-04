package com.kingjinho.dontcallhim.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kingjinho.dontcallhim.usecase.add.AddNumberUseCase
import com.kingjinho.dontcallhim.usecase.fetch.FetchNumbersUseCase
import com.kingjinho.dontcallhim.utils.isValidPhoneNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OutgoingCallVM @Inject constructor(
    private val addNumberUseCase: AddNumberUseCase,
    private val fetchNumbersUseCase: FetchNumbersUseCase
) : ViewModel() {

    private var _addNumberResult = mutableStateOf<Result?>(null)
    val addNumberResult: State<Result?>
        get() = _addNumberResult

    fun addNumber(number: String) {
        viewModelScope.launch {
            if (!number.isValidPhoneNumber()) {
                _addNumberResult.value = Result.Failure
            } else {
                addNumberUseCase.invoke(number.replace("-", ""))
                _addNumberResult.value = Result.Success
            }
        }
    }
    fun resetResult() {
        _addNumberResult.value = null
    }
    fun fetchSavedNumbers() = fetchNumbersUseCase.invoke()
}

sealed class Result {
    object Success : Result()
    object Failure : Result()
}