package com.kingjinho.dontcallhim.di.application

import com.kingjinho.dontcallhim.db.AppDatabase
import com.kingjinho.dontcallhim.di.activity.ActivityComponent
import com.kingjinho.dontcallhim.di.activity.ActivityModule
import com.kingjinho.dontcallhim.di.scope.AppScope
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun getDatabase(): AppDatabase

    fun newActivityComponent(activityModule: ActivityModule): ActivityComponent
}