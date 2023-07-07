package com.kingjinho.dontcallhim

import android.app.Application
import com.kingjinho.dontcallhim.db.AppDatabase

class DontCallHimApplication: Application() {

    val dbInstance: AppDatabase by lazy {
        AppDatabase.getInstance(applicationContext)
    }

}