// Put your package name here. Check your activity for reference.
package com.example.mobileapp_project

import junit.framework.TestCase.assertEquals
import org.junit.Test

class CredentialsManagerTest {

    // Test empty email
    @Test
    fun givenEmptyEmail_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        assertEquals(false, credentialsManager.isEmailValid(""))

    }
    // Test wrong email format
    @Test
    fun givenWrongEmailFormat_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        assertEquals(false, credentialsManager.isEmailValid("email@.rt"))
    }
    // Test proper email
    @Test
    fun givenProperEmail_thenReturnTrue() {
        val credentialsManager = CredentialsManager()
        assertEquals(true, credentialsManager.isEmailValid("email@example.com"))
    }
    // Test empty password
    @Test
    fun givenEmptyPassword_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        assertEquals(false, credentialsManager.isPasswordValid(""))
    }
    // Test filled password
    @Test
    fun givenValidPassword_thenReturnTrue() {
        val credentialsManager = CredentialsManager()
        assertEquals(true, credentialsManager.isPasswordValid("Password@1234"))
    }
}