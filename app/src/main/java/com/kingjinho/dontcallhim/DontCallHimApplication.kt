package com.kingjinho.dontcallhim

import android.app.Application
import com.kingjinho.dontcallhim.db.AppDatabase

class DontCallHimApplication: Application() {

    val instance: AppDatabase by lazy {
        AppDatabase.getInstance(applicationContext)
    }

}