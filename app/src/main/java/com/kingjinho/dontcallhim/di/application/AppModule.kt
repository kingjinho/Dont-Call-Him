package com.kingjinho.dontcallhim.di.application

import android.app.Application
import com.kingjinho.dontcallhim.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(value = [SingletonComponent::class])
object AppModule {

    @Provides
    @AppScope
    fun getDatabase(application: Application) =
        AppDatabase.getInstance(application.applicationContext)

}