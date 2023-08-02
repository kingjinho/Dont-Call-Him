package com.kingjinho.dontcallhim.screen.main

import android.app.Activity
import android.app.role.RoleManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kingjinho.dontcallhim.R

@Composable
fun MainScreen(onAddNumberClick: () -> Unit) {
    Column {
        val context = LocalContext.current

        val callRedirectionContract = object : ActivityResultContract<Any?, Int>() {
            override fun createIntent(context: Context, input: Any?): Intent {
                val roleManager = context.getSystemService(RoleManager::class.java)
                return roleManager.createRequestRoleIntent(RoleManager.ROLE_CALL_REDIRECTION)
            }

            override fun parseResult(resultCode: Int, intent: Intent?): Int {
                return resultCode
            }
        }

        val callRedirectionCallback =
            ActivityResultCallback<Int> { resultCode ->
                if (resultCode != Activity.RESULT_OK) {
                    showToast(context, R.string.msg_error_not_being_default_app, Toast.LENGTH_LONG)
                } else {
                    onAddNumberClick()
                }
            }

        val requestRedirectionRole =
            rememberLauncherForActivityResult(contract = callRedirectionContract, onResult = {
                callRedirectionCallback.onActivityResult(it)
            })

        Text(
            modifier = Modifier.height(500.dp),
            text = stringResource(id = R.string.msg_do_not_call_him),
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 96.sp,
                lineHeight = 100.sp,
                color = isSystemInDarkTheme().let {
                    if (it) {
                        MaterialTheme.colorScheme.onBackground
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    }
                }
            )
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 24.dp),
            shape = MaterialTheme.shapes.medium,
            onClick = {
                if (context.getSystemService(RoleManager::class.java)
                        .isRoleAvailable(RoleManager.ROLE_CALL_REDIRECTION)
                ) {
                    requestRedirectionRole.launch(null)
                } else {
                    showToast(
                        context,
                        R.string.msg_phoe_does_not_allow_role_required,
                        Toast.LENGTH_SHORT
                    )
                }
            },
        ) {
            Text(text = stringResource(id = R.string.msg_add_number_you_do_not_want_to_make))
        }
    }
}

private fun showToast(context: Context, msgRes: Int, duration: Int) {
    Toast.makeText(context, context.getString(msgRes), duration).show()
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun MainScreenPreview() {
    MainScreen(onAddNumberClick = {})
}