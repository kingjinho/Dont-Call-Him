package com.kingjinho.dontcallhim.service

import android.net.Uri
import android.telecom.CallRedirectionService
import android.telecom.PhoneAccountHandle
import com.kingjinho.dontcallhim.DontCallHimApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OutgoingCallServiceImpl : CallRedirectionService() {

    private val dbInstance by lazy {
        (application as DontCallHimApplication).appComponent.getDatabase()
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
