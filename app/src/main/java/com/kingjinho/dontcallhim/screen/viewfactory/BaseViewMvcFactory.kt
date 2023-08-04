package com.kingjinho.dontcallhim.screen.viewfactory

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kingjinho.dontcallhim.screen.add.ScreenAddNumberMvc
import javax.inject.Inject

class BaseViewMvcFactory @Inject constructor(private val layoutInflater: LayoutInflater) {

    fun newAddNumberMvc(parent: ViewGroup?): ScreenAddNumberMvc {
        return ScreenAddNumberMvc(layoutInflater, parent)
    }

}