package com.example.mobileapp_project

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class LoginFragment : Fragment(R.layout.login_page) {

    var credentialsManager: CredentialsManager? = null
    private var listener: EventInterface? = null
    interface EventInterface {
        fun onRegisterPressed()
    }

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

        val registerTextView: TextView = view.findViewById(R.id.register)
        registerTextView.setOnClickListener {
            Log.d("LoginFragment", "Register now clicked")
            listener?.onRegisterPressed()
        }
    }
}
