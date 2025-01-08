package com.example.mobileapp_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private val credentialsManager = CredentialsManager()

    private val emailInputLayout: TextInputLayout
        get() = findViewById(R.id.email_input)

    private val email: String
        get() = emailInputLayout.editText?.text?.toString().orEmpty()

    private val passwordInputLayout: TextInputLayout
        get() = findViewById(R.id.password_input)

    private val password: String
        get() = passwordInputLayout.editText?.text?.toString().orEmpty()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        findViewById<TextView>(R.id.register).setOnClickListener {
            Log.d("MainActivity", "Pressed register label")
            val registerNow = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(registerNow)
            finish()
        }

        findViewById<Button>(R.id.next_button).setOnClickListener {
            if (credentialsManager.isEmailValid(email) && credentialsManager.isPasswordValid(password)) {
                Log.d("MainActivity", "Valid credentials")
                val nextIntent = Intent(this@MainActivity, NewActivity::class.java)
                startActivity(nextIntent)
                finish()
            } else {
                if (!credentialsManager.isEmailValid(email)) {
                    emailInputLayout.error = "Invalid email format"
                } else {
                    emailInputLayout.error = null
                }

                if (!credentialsManager.isPasswordValid(password)) {
                    passwordInputLayout.error = "Invalid password format"
                } else {
                    passwordInputLayout.error = null
                }
            }
        }
    }
}