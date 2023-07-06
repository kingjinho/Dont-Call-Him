package com.kingjinho.dontcallhim.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kingjinho.dontcallhim.R

@Composable
fun MainScreen() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MainTitle(stringResource(id = R.string.msg_do_not_call_him))

        Button(onClick = {
        }) {
            Text(text = stringResource(id = R.string.msg_add_number_you_do_not_want_to_make))
        }
    }
}

@Composable
fun MainTitle(name: String) {
    Text(
        style = MaterialTheme.typography.h1,
        text = "$name!"
    )
}
