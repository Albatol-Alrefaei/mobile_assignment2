package com.example.mobileapp_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_account)
        val loginLabel = findViewById<TextView>(R.id.login)
        loginLabel.setOnClickListener {
            Log.d("RegisterActivity", "Pressed login label")

            val goToLoginIntent = Intent(this, MainActivity::class.java)
            startActivity(goToLoginIntent)
            finish()
        }
    }
}
