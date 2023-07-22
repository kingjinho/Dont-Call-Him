package com.kingjinho.dontcallhim.di.activity

import android.app.Activity
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(value = [ActivityComponent::class])
object ActivityModule {

    @Provides
    fun activity(activity: Activity) = activity as AppCompatActivity

    @Provides
    fun layoutInflater(activity: AppCompatActivity): LayoutInflater = LayoutInflater.from(activity)

}