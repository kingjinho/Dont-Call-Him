package com.kingjinho.dontcallhim

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kingjinho.dontcallhim.ui.theme.DontCallHimApp
import com.kingjinho.dontcallhim.ui.theme.DontCallHimTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DontCallHimActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DontCallHimTheme {
                DontCallHimApp()
            }
        }
    }
}