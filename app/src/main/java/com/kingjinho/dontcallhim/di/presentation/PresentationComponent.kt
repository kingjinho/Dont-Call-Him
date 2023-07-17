package com.kingjinho.dontcallhim.di.presentation

import com.kingjinho.dontcallhim.usecase.add.AddNumberUseCase
import com.kingjinho.dontcallhim.usecase.fetch.FetchNumbersUseCase
import dagger.Subcomponent


@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {

    fun getAddNumberUseCase(): AddNumberUseCase

    fun getFetchNumbersUseCase(): FetchNumbersUseCase

}