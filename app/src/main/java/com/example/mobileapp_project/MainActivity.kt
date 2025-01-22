package com.example.mobileapp_project
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var credentialsManager: CredentialsManager? = null
    private val emailInputLayout: TextInputLayout
        get() = findViewById(R.id.emailInput)

    private val email: String
        get() = emailInputLayout.editText?.text?.toString().orEmpty()

    private val passwordInputLayout: TextInputLayout
        get() = findViewById(R.id.passwordInput)

    private val password: String
        get() = passwordInputLayout.editText?.text?.toString().orEmpty()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        credentialsManager = CredentialsManager.getInstance()

        val loginButton: Button = findViewById(R.id.nextButton)
        loginButton.setOnClickListener {
            if (credentialsManager?.isEmailValid(email) == true && credentialsManager?.isPasswordValid(password) == true) {
                lifecycleScope.launch {
                    if (credentialsManager?.login(email, password) == true) {
                        Log.d("MainActivity", "Login successful")
                        navigateToRecipeActivity()
                    } else {
                        Log.d("MainActivity", "Login failed")
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

        val registerTextView: TextView = findViewById(R.id.register)
        registerTextView.setOnClickListener {
            Log.d("MainActivity", "Register now clicked")
            val registerIntent = Intent(this, RegisterActivity::class.java)
            startActivity(registerIntent)
            finish()
        }
    }

    private fun navigateToRecipeActivity() {
        val intent = Intent(this, RecipeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
