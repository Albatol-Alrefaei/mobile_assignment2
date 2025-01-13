package com.example.mobileapp_project

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun stringEqualsTest()
    {
        assertTrue("AAA".equals("AAA"))
        assertFalse("AAA".equals("aaa"))
        assertTrue("AAA".equals("aaa",true))
        assertTrue("AAA".lowercase().equals("aaa"))
    }
}