package com.kingjinho.dontcallhim.screen.add

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kingjinho.dontcallhim.R
import com.kingjinho.dontcallhim.utils.PhoneNumberVisualTransformation
import com.kingjinho.dontcallhim.viewmodels.OutgoingCallVM
import com.kingjinho.dontcallhim.viewmodels.Result


@Composable
fun AddNumberScreen(
    viewModel: OutgoingCallVM
) {
    val scrollState = rememberScrollState()
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    val phoneNumbers by viewModel.fetchSavedNumbers().collectAsStateWithLifecycle(
        initialValue = emptyList()
    )

    Column(
        modifier = Modifier
            .padding(24.dp)
            .verticalScroll(scrollState)
    ) {
        val context = LocalContext.current
        if (viewModel.addNumberResult.value == Result.Failure) {
            Toast.makeText(context, R.string.msg_invalid_number, Toast.LENGTH_SHORT)
                .show()
            viewModel.resetResult()
        }

        PhoneNumberTextField(
            modifier = Modifier,
            phoneNumber = phoneNumber,
            onValueChange = {
                phoneNumber = it
            },
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = MaterialTheme.shapes.medium,
            onClick = {
                viewModel.addNumber(phoneNumber)
            })
        {
            Text(text = stringResource(id = R.string.text_add))
        }

        Spacer(modifier = Modifier.height(24.dp))

        if(phoneNumbers.isNotEmpty()) {
            phoneNumbers.forEach {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(start = 12.dp)
                            .wrapContentHeight(),
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 24.sp),
                        text = it.formattedNumber,
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun PhoneNumberTextField(
    modifier: Modifier = Modifier,
    phoneNumber: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = phoneNumber, onValueChange = onValueChange,
        placeholder = { Text(text = stringResource(id = R.string.msg_add_number_you_do_not_want_to_make)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        visualTransformation = PhoneNumberVisualTransformation()
    )
}