package com.kingjinho.dontcallhim.di.presentation

import com.kingjinho.dontcallhim.screen.add.ScreenAddNumber
import dagger.Subcomponent


@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {

    fun inject(fragment: ScreenAddNumber)

}