package com.kingjinho.dontcallhim.service

import android.net.Uri
import android.telecom.CallRedirectionService
import android.telecom.PhoneAccountHandle
import com.kingjinho.dontcallhim.DontCallHimApplication
import com.kingjinho.dontcallhim.db.AppDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class OutgoingCallServiceImpl : CallRedirectionService() {

    @Inject lateinit var dbInstance: AppDatabase

    override fun onCreate() {
        super.onCreate()
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
