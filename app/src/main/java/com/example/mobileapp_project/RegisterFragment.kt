package com.example.mobileapp_project

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class RegisterFragment : Fragment(R.layout.create_account) {

    var credentialsManager: CredentialsManager? = null
    private var listener: EventInterface? = null

    interface EventInterface {
        fun onLoginPressed()
    }

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

        val loginTextView: TextView = view.findViewById(R.id.login)
        loginTextView.setOnClickListener {
            Log.d("RegisterFragment", "Login now clicked")
            listener?.onLoginPressed()
        }
    }
}
