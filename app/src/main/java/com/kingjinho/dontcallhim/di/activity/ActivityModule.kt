package com.kingjinho.dontcallhim.di.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.kingjinho.dontcallhim.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

@Module
object ActivityModule {

    @Provides
    @ActivityScope
    fun layoutInflater(activity: AppCompatActivity): LayoutInflater = LayoutInflater.from(activity)

}