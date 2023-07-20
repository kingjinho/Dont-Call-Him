package com.kingjinho.dontcallhim.di.activity

import androidx.appcompat.app.AppCompatActivity
import com.kingjinho.dontcallhim.di.presentation.PresentationComponent
import com.kingjinho.dontcallhim.di.presentation.PresentationModule
import com.kingjinho.dontcallhim.di.scope.ActivityScope
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [ActivityModule::class])
@ActivityScope
interface ActivityComponent {

    fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent

    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        fun activity(activity: AppCompatActivity): Builder
        fun activityModule(activityModule: ActivityModule): Builder
        fun build(): ActivityComponent
    }
}