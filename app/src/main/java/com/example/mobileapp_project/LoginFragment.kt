package com.example.mobileapp_project
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

class LoginFragment : Fragment(R.layout.login_page) {

    private var credentialsManager: CredentialsManager? = null
    private var listener: EventInterface? = null

    interface EventInterface {
        fun onRegisterPressed()
        fun onLoginSuccess()
    }

    private val emailInputLayout: TextInputLayout
        get() = requireView().findViewById(R.id.emailInput)

    private val email: String
        get() = emailInputLayout.editText?.text?.toString().orEmpty()

    private val passwordInputLayout: TextInputLayout
        get() = requireView().findViewById(R.id.passwordInput)

    private val password: String
        get() = passwordInputLayout.editText?.text?.toString().orEmpty()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is EventInterface) {
            listener = context
        } else {
            throw ClassCastException("$context must implement LoginFragment.EventInterface")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        credentialsManager = CredentialsManager.getInstance()
        val loginButton: Button = view.findViewById(R.id.nextButton)
        loginButton.setOnClickListener {
            if (credentialsManager?.isEmailValid(email) == true && credentialsManager?.isPasswordValid(password) == true) {
                lifecycleScope.launch {
                    if (credentialsManager?.login(email, password) == true) {
                        listener?.onLoginSuccess()
                    }
                }
            } else {
                if (credentialsManager?.isEmailValid(email) != true) {
                    emailInputLayout.error = "Invalid email format"
                } else {
                    emailInputLayout.error = null
                }

                if (credentialsManager?.isPasswordValid(password) != true) {
                    passwordInputLayout.error = "Invalid password format"
                } else {
                    passwordInputLayout.error = null
                }
            }
        }

        val registerTextView: TextView = view.findViewById(R.id.register)
        registerTextView.setOnClickListener {
            listener?.onRegisterPressed()
        }
    }

    fun setCredentialsManager(manager: CredentialsManager) {
        this.credentialsManager = manager
    }
}
