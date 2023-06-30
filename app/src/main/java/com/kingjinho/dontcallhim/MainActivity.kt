package com.kingjinho.dontcallhim

import android.app.Activity
import android.app.role.RoleManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.kingjinho.dontcallhim.service.OutgoingCallServiceImpl
import com.kingjinho.dontcallhim.ui.theme.DontCallHimTheme

class MainActivity : ComponentActivity() {

    private val callRedirectionContract = object : ActivityResultContract<Any, Int>() {
        override fun createIntent(context: Context, input: Any?): Intent {
            val roleManager = getSystemService(RoleManager::class.java)
            return roleManager.createRequestRoleIntent(RoleManager.ROLE_CALL_REDIRECTION)
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Int {
            return resultCode
        }
    }

    private val callRedirectionCallback =
        ActivityResultCallback<Int> { resultCode ->
            if (resultCode != Activity.RESULT_OK) {
                Toast.makeText(this, "기본 앱으로 설정 하셔야 작동합니다.", Toast.LENGTH_LONG).show()
                return@ActivityResultCallback
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DontCallHimTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Greeting("제발 술먹고 걔한테 전화좀 하지마.. ")

                        Button(onClick = {

                        }) {
                            Text(text = stringResource(id = R.string.msg_add_number_you_do_not_want_to_make))
                        }
                    }
                }
            }
        }
        if (!isRedirection()) {
            roleAcquire(RoleManager.ROLE_CALL_REDIRECTION)
        }
    }

    private fun isRedirection(): Boolean {
        return isRoleHeldByApp(RoleManager.ROLE_CALL_REDIRECTION)
    }

    private fun isRoleHeldByApp(role: String): Boolean {
        return getSystemService(RoleManager::class.java).isRoleHeld(role)
    }

    private fun roleAcquire(roleName: String) {
        if (roleAvailable(roleName)) {
            registerForActivityResult(callRedirectionContract,
                callRedirectionCallback).launch(null)
        } else {
            Toast.makeText(this,
                "Redirection call with role in not available",
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun roleAvailable(roleName: String): Boolean {
        return getSystemService(RoleManager::class.java).isRoleAvailable(roleName)
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        style = MaterialTheme.typography.h1,
        text = "$name!"
    )
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun DefaultPreview() {
    DontCallHimTheme {
        Greeting("제발 술먹고 걔한테 전화좀 하지마..!!")
    }
}
