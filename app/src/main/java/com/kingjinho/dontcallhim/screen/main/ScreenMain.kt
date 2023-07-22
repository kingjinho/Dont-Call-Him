package com.kingjinho.dontcallhim.screen.main

import android.app.Activity
import android.app.role.RoleManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContract
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kingjinho.dontcallhim.R
import com.kingjinho.dontcallhim.databinding.ScreenMainBinding
import dagger.hilt.android.AndroidEntryPoint

class ScreenMain : Fragment() {

    private lateinit var binding: ScreenMainBinding

    private val callRedirectionContract = object : ActivityResultContract<Any?, Int>() {
        override fun createIntent(context: Context, input: Any?): Intent {
            val roleManager = requireContext().getSystemService(RoleManager::class.java)
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
                    requireContext(),
                    getString(R.string.msg_error_not_being_default_app),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                toAddNumberScreen()
            }
        }

    private val requestRedirectionRole = registerForActivityResult(
        callRedirectionContract, callRedirectionCallback
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ScreenMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toAddNumber.setOnClickListener {
            if (!hasRedirectionRole()) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.msg_role_required_to_block_outgoing_call),
                    Toast.LENGTH_LONG
                )
                    .show()
                requestRedirectionRole()
            } else {
                toAddNumberScreen()
            }
        }
    }

    private fun toAddNumberScreen() {
        findNavController().navigate(R.id.screenAddRemoveNumber)
    }

    private fun hasRedirectionRole(): Boolean {
        return requireContext().getSystemService(RoleManager::class.java)
            .isRoleHeld(RoleManager.ROLE_CALL_REDIRECTION)
    }

    private fun requestRedirectionRole() {
        if (roleAvailable()) {
            requestRedirectionRole.launch(null)
        } else {
            Toast.makeText(
                requireContext(),
                getString(R.string.msg_phoe_does_not_allow_role_required),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun roleAvailable(): Boolean {
        return requireContext().getSystemService(RoleManager::class.java)
            .isRoleAvailable(RoleManager.ROLE_CALL_REDIRECTION)
    }

}