package com.kingjinho.dontcallhim.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(providers.containsKey(modelClass)) {
            return providers[modelClass]!!.get() as T
        }
        throw IllegalArgumentException("modelClass is not assignable: ${modelClass.canonicalName}")
    }
}
