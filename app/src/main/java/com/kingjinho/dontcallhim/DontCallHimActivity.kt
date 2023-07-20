package com.kingjinho.dontcallhim

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kingjinho.dontcallhim.di.activity.ActivityComponent
import com.kingjinho.dontcallhim.di.activity.ActivityModule

class DontCallHimActivity : AppCompatActivity() {

    val activityComponent: ActivityComponent by lazy {
        (application as DontCallHimApplication).appComponent
            .newActivityComponentBuilder()
            .activity(this)
            .activityModule(ActivityModule)
            .build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
