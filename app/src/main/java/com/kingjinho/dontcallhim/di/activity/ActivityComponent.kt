package com.kingjinho.dontcallhim.di.activity

import com.kingjinho.dontcallhim.di.presentation.PresentationComponent
import com.kingjinho.dontcallhim.di.presentation.PresentationModule
import com.kingjinho.dontcallhim.di.scope.ActivityScope
import dagger.Component
import dagger.Subcomponent

@Subcomponent(modules = [ActivityModule::class])
@ActivityScope
interface ActivityComponent {

    fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent
}