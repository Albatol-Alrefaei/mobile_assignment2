package com.example.mobileapp_project

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout

class RegisterFragment : Fragment(R.layout.create_account) {

    var credentialsManager: CredentialsManager? = null
    private var listener: EventInterface? = null

    interface EventInterface {
        fun onLoginPressed()
    }

    private val fullNameInputLayout: TextInputLayout
        get() = requireView().findViewById(R.id.fullName)

    private val fullName: String
        get() = fullNameInputLayout.editText?.text?.toString().orEmpty()

    private val emailInputLayout: TextInputLayout
        get() = requireView().findViewById(R.id.emailAddress)

    private val email: String
        get() = emailInputLayout.editText?.text?.toString().orEmpty()

    private val phoneNumberInputLayout: TextInputLayout
        get() = requireView().findViewById(R.id.phoneNumber)

    private val phoneNumber: String
        get() = phoneNumberInputLayout.editText?.text?.toString().orEmpty()

    private val passwordInputLayout: TextInputLayout
        get() = requireView().findViewById(R.id.password)

    private val password: String
        get() = passwordInputLayout.editText?.text?.toString().orEmpty()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is EventInterface) {
            listener = context
        } else {
            throw ClassCastException("$context must implement RegisterFragment.EventInterface")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        credentialsManager = CredentialsManager.getInstance()

        val loginTextView: TextView = view.findViewById(R.id.login)
        loginTextView.setOnClickListener {
            Log.d("RegisterFragment", "Login now clicked")
            listener?.onLoginPressed()
        }

        val registerButton: Button = view.findViewById(R.id.nextButton)
        registerButton.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        var isValid = true

        if (fullName.isEmpty()) {
            fullNameInputLayout.error = "Full name is required"
            isValid = false
        } else {
            fullNameInputLayout.error = null
        }

        if (credentialsManager?.isEmailValid(email) != true) {
            emailInputLayout.error = "Invalid email format"
            isValid = false
        } else {
            emailInputLayout.error = null
        }

        if (phoneNumber.isEmpty() || phoneNumber.length < 10) {
            phoneNumberInputLayout.error = "Phone number must have at least 10 digits"
            isValid = false
        } else {
            phoneNumberInputLayout.error = null
        }

        if (credentialsManager?.isPasswordValid(password) != true) {
            passwordInputLayout.error = "Invalid password format"
            isValid = false
        } else {
            passwordInputLayout.error = null
        }

        if (isValid) {
            val registrationSuccess = credentialsManager?.register(fullName, email, phoneNumber, password) == true
            if (registrationSuccess) {
                Log.d("RegisterFragment", "User registered successfully")
                listener?.onLoginPressed()
            } else {
                emailInputLayout.error = "Email is already registered"
            }
        }
    }
}
