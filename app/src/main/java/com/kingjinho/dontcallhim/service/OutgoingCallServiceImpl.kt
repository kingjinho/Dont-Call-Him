package com.kingjinho.dontcallhim.service

import android.net.Uri
import android.telecom.CallRedirectionService
import android.telecom.PhoneAccountHandle
import com.kingjinho.dontcallhim.DontCallHimApplication
import com.kingjinho.dontcallhim.db.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class OutgoingCallServiceImpl : CallRedirectionService() {

    private val appComponent get() = (application as DontCallHimApplication).appComponent
    private val serviceComponent by lazy {
        appComponent.newServiceComponent()
    }

    @Inject lateinit var dbInstance: AppDatabase

    override fun onCreate() {
        super.onCreate()
        serviceComponent.inject(this)
    }

    override fun onPlaceCall(
        handle: Uri,
        initialPhoneAccount: PhoneAccountHandle,
        allowInteractiveResponse: Boolean
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            if (dbInstance.phoneNumberDao().getExistingData(handle.schemeSpecificPart)) {
                cancelCall()
            } else {
                placeCallUnmodified()
            }
        }
    }

}
