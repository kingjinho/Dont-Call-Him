package com.kingjinho.dontcallhim.di.application

import android.app.Application
import com.kingjinho.dontcallhim.di.activity.ActivityComponent
import com.kingjinho.dontcallhim.di.activity.ActivityModule
import com.kingjinho.dontcallhim.di.scope.AppScope
import com.kingjinho.dontcallhim.di.service.ServiceComponent
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun newActivityComponentBuilder(): ActivityComponent.Builder

    fun newServiceComponent(): ServiceComponent

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun appModule(appModule: AppModule): Builder
        fun build(): AppComponent
    }
}