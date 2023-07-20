package com.kingjinho.dontcallhim.di.application

import com.kingjinho.dontcallhim.di.activity.ActivityComponent
import com.kingjinho.dontcallhim.di.activity.ActivityModule
import com.kingjinho.dontcallhim.di.scope.AppScope
import com.kingjinho.dontcallhim.di.service.ServiceComponent
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun newActivityComponent(activityModule: ActivityModule): ActivityComponent

    fun newServiceComponent(): ServiceComponent
}