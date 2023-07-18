package com.kingjinho.dontcallhim.screen

import androidx.fragment.app.Fragment
import com.kingjinho.dontcallhim.DontCallHimActivity
import com.kingjinho.dontcallhim.di.presentation.PresentationComponent
import com.kingjinho.dontcallhim.di.presentation.PresentationModule

open class BaseScreen: Fragment() {

    private val presentationComponent: PresentationComponent by lazy {
        (requireActivity() as DontCallHimActivity).activityComponent.newPresentationComponent(
            PresentationModule()
        )
    }

    protected val injector get() = presentationComponent
}