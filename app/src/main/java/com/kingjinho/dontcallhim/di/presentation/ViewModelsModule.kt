package com.kingjinho.dontcallhim.di.presentation

import androidx.lifecycle.ViewModel
import com.kingjinho.dontcallhim.di.key.ViewModelKey
import com.kingjinho.dontcallhim.viewmodels.OutgoingCallVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(value = OutgoingCallVM::class)
    abstract fun outgoingCallViewModel(actual: OutgoingCallVM): ViewModel
}