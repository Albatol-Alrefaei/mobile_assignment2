
package com.example.mobileapp_project
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CredentialsManager {
    val credentials = mutableMapOf<String, String>(
    )
    companion object {
        @Volatile private var instance: CredentialsManager? = null

        fun getInstance(): CredentialsManager {
            return instance ?: synchronized(this) {
                instance ?: CredentialsManager().also { instance = it }
            }
        }
    }
    fun clearCredentials() {
        credentials.clear()
    }
    private val _loginState = MutableStateFlow(false)
    val loginState: StateFlow<Boolean> get() = _loginState
    fun isEmailValid(email: String): Boolean {
        val emailPattern = ("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+")
        val regex = Regex(emailPattern)
        return regex.matches(email)
    }

    fun isPasswordValid(password: String): Boolean {
        val passwordPattern = "^\\d{4,}$"
        val regex = Regex(passwordPattern)
        return regex.matches(password)
    }

    fun login(email: String, password: String): Boolean {
        if (credentials[email.lowercase()] == password) {
            _loginState.value = true
            return true
        }
        return false
    }

    fun logout() {
        _loginState.value = false
        clearCredentials()
    }

    fun register(fullName: String, email: String, phoneNumber: String, password: String): Boolean {
        val normalizedEmail = email.lowercase()
        println("Attempting to register: $normalizedEmail")
        println("Current stored emails: ${credentials.keys}")
        if (credentials.containsKey(normalizedEmail)) {
            println("Email already exists: $normalizedEmail")
            return false
        }
        credentials[normalizedEmail] = password
        println("Registering new email: $normalizedEmail")
        println("Current stored emails after registration: ${credentials.keys}")
        return true
    }
    }

