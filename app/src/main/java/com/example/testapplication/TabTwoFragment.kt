package com.example.testapplication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_two.*
import java.util.concurrent.Executor

class TabTwoFragment(context: Context) : Fragment() {
    val myContext = context
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val executor = ContextCompat.getMainExecutor(myContext)
        val biometricManager = BiometricManager.from(myContext)

        ib_fingerprint.setOnClickListener {
            tv_authentication_message.text = ""
            when (biometricManager.canAuthenticate()) {
                BiometricManager.BIOMETRIC_SUCCESS ->
                    authUser(executor)
                BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                    tv_authentication_message.text = getString(R.string.error_msg_no_biometric_hardware)
                BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                    tv_authentication_message.text = getString(R.string.error_msg_biometric_hw_unavailable)
                BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                    tv_authentication_message.text = getString(R.string.error_msg_biometric_not_setup)
            }
        }
    }

    private fun authUser(executor: Executor) {
        // 1
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            // 2
            .setTitle(getString(R.string.auth_title))
            // 3
            .setSubtitle(getString(R.string.auth_subtitle))
            // 4
            .setDescription(getString(R.string.auth_description))
            // 5
            .setDeviceCredentialAllowed(true)
            // 6
            .build()

        // 1
        val biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                // 2
                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    tv_authentication_message.text = getString(R.string.success_msg)
                }
                // 3
                override fun onAuthenticationError(
                    errorCode: Int, errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    tv_authentication_message.text = getString(R.string.error_msg_auth_error, errString)
                }
                // 4
                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    tv_authentication_message.text = getString(R.string.error_msg_auth_failed)
                }
            })

        biometricPrompt.authenticate(promptInfo)
    }
}