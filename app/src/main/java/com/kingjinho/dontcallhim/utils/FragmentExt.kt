package com.kingjinho.dontcallhim.utils

import androidx.fragment.app.Fragment
import com.kingjinho.dontcallhim.DontCallHimApplication
import com.kingjinho.dontcallhim.di.application.AppComponent

fun Fragment.getAppComponent(): AppComponent =
    (requireActivity().application as DontCallHimApplication).appComponent