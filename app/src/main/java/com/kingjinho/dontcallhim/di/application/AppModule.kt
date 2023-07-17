package com.kingjinho.dontcallhim.di.application

import android.app.Application
import com.kingjinho.dontcallhim.db.AppDatabase
import com.kingjinho.dontcallhim.di.scope.AppScope
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: Application) {

    @Provides
    fun getApplication() = application

    @Provides
    @AppScope
    fun getDatabase(application: Application): AppDatabase =
        AppDatabase.getInstance(application.applicationContext)

}