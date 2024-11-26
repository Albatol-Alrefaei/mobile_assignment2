package com.example.mobileapp_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        val registerNowLabel = findViewById<TextView>(R.id.register)
        registerNowLabel.setOnClickListener {
            Log.d("LoginActivity", "Pressed register now label")

            val goToRegisterIntent = Intent(this, RegisterActivity::class.java)
            startActivity(goToRegisterIntent)
            finish()
        }
    }
}