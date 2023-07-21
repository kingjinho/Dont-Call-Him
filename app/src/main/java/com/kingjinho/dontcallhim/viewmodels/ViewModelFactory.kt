package com.kingjinho.dontcallhim.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val outgoingCallVMProvider: Provider<OutgoingCallVM>,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            OutgoingCallVM::class.java -> outgoingCallVMProvider.get() as T
            else -> throw IllegalArgumentException("modelClass is not assignable: ${modelClass.canonicalName}")
        }
    }
}
