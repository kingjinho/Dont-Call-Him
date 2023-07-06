package com.kingjinho.dontcallhim

import androidx.compose.runtime.Composable
import com.kingjinho.dontcallhim.screen.MainScreen
import com.kingjinho.dontcallhim.screen.OutgoingCallListScreen

interface DontCallHimDestination {
    val route: String
    val screen: @Composable () -> Unit
}

object Main : DontCallHimDestination {
    override val route: String = "main"
    override val screen: @Composable () -> Unit = { MainScreen() }
}

object OutgoingCallList : DontCallHimDestination {
    override val route: String = "outgoing"
    override val screen: @Composable () -> Unit = { OutgoingCallListScreen() }
}