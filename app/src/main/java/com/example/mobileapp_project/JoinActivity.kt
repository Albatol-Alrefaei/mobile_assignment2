package com.example.mobileapp_project

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit

class JoinActivity : AppCompatActivity(R.layout.join_page),
    LoginFragment.EventInterface, RegisterFragment.EventInterface {

    private var credentialsManager = CredentialsManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragment_container_view)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                val loginFragment = LoginFragment()
                loginFragment.credentialsManager = credentialsManager
                replace(R.id.fragment_container_view, loginFragment)
            }
        }
    }
    override fun onRegisterPressed() {
        Log.d("JoinActivity", "Switching to RegisterFragment")
        val registerFragment = RegisterFragment().apply {
            credentialsManager = this@JoinActivity.credentialsManager
        }

        supportFragmentManager.commit {
            replace(R.id.fragment_container_view, registerFragment)
            addToBackStack(null)
        }
    }
    override fun onLoginPressed() {
        Log.d("JoinActivity", "Switching to LoginFragment")
        val loginFragment = LoginFragment().apply {
            credentialsManager = this@JoinActivity.credentialsManager
        }

        supportFragmentManager.commit {
            replace(R.id.fragment_container_view, loginFragment)
            addToBackStack(null)
        }
    }
}




