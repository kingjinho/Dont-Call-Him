package com.kingjinho.dontcallhim.service

import android.content.Intent
import android.os.IBinder
import android.telecom.Call
import android.telecom.CallScreeningService

class CallScreeningServiceImpl : CallScreeningService() {

    //one-time setup procedure when the service is initially created
    //called before onStartCommand or onBind
    //if service is running, this won't be called
    override fun onCreate() {
        super.onCreate()
    }

    //when service is no longer used and being destroyed
    //clean up any resources
    //last call
    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onScreenCall(callDetails: Call.Details) {
        respondToCall(
            callDetails, CallResponse.Builder()
                .setDisallowCall(true)
                .setSkipNotification(true)
                .setSkipCallLog(true)
                .build()
        )
    }

    //when startService is called from another component(e.g. Activity)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    //when bindService is called from another component to bind with service
    override fun onBind(intent: Intent?): IBinder? {
        return super.onBind(intent)
    }

}
