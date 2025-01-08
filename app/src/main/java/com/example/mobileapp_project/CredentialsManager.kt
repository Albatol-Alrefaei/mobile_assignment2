
package com.example.mobileapp_project


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
        return credentials[email.lowercase()] == password
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

