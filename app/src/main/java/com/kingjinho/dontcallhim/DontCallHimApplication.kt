package com.kingjinho.dontcallhim

import android.app.Application
import com.kingjinho.dontcallhim.db.AppDatabase
import com.kingjinho.dontcallhim.di.application.AppComponent
import com.kingjinho.dontcallhim.di.application.AppModule
import com.kingjinho.dontcallhim.di.application.DaggerAppComponent

class DontCallHimApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}