package com.kingjinho.dontcallhim.di.application

import android.app.Application
import com.kingjinho.dontcallhim.db.AppDatabase
import com.kingjinho.dontcallhim.di.scope.AppScope
import dagger.Module
import dagger.Provides

@Module
object AppModule {

    @Provides
    @AppScope
    fun getDatabase(application: Application) =
        AppDatabase.getInstance(application.applicationContext)

}