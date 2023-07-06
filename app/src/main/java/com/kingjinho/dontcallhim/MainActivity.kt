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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kingjinho.dontcallhim.ui.theme.DontCallHimTheme

class MainActivity : ComponentActivity() {

    private val callRedirectionContract = object : ActivityResultContract<Any, Int>() {
        override fun createIntent(context: Context, input: Any): Intent {
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
                Toast.makeText(
                    this,
                    getString(R.string.msg_error_not_being_default_app),
                    Toast.LENGTH_LONG
                ).show()
                return@ActivityResultCallback
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DontCallHimApp()
        }
//        if (!isRedirection()) {
//            roleAcquire(RoleManager.ROLE_CALL_REDIRECTION)
//        }
    }

    private fun isRedirection(): Boolean {
        return isRoleHeldByApp(RoleManager.ROLE_CALL_REDIRECTION)
    }

    private fun isRoleHeldByApp(role: String): Boolean {
        return getSystemService(RoleManager::class.java).isRoleHeld(role)
    }

    private fun roleAcquire(roleName: String) {
        if (roleAvailable(roleName)) {
            registerForActivityResult(
                callRedirectionContract,
                callRedirectionCallback
            ).launch(null)
        } else {
            Toast.makeText(
                this,
                getString(R.string.msg_phoe_does_not_allow_role_required),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun roleAvailable(roleName: String): Boolean {
        return getSystemService(RoleManager::class.java).isRoleAvailable(roleName)
    }
}

@Composable
fun DontCallHimApp() {
    DontCallHimTheme {
        val navController = rememberNavController()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Scaffold {
                NavHost(
                    navController = navController,
                    startDestination = Main.route,
                    modifier = Modifier.padding(it)
                ) {
                    composable(route = Main.route) {
                        Main.screen()
                    }
                    composable(route = OutgoingCallList.route) {
                        OutgoingCallList.screen()
                    }
                }
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun DefaultPreview() {
    DontCallHimTheme {

    }
}
