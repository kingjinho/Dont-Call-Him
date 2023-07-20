package com.kingjinho.dontcallhim.di.service

import com.kingjinho.dontcallhim.service.OutgoingCallServiceImpl
import dagger.Subcomponent

@Subcomponent
interface ServiceComponent {

    fun inject(service: OutgoingCallServiceImpl)

}