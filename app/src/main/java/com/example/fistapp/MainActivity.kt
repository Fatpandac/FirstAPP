package com.example.fistapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.login)
        btn.setOnClickListener {
            val accountInput = findViewById<EditText>(R.id.account)
            val passwordInput = findViewById<EditText>(R.id.password)
            Log.e("Login:", "${passwordInput.text} ${accountInput.text}")
            if (accountInput.text.toString() == "Fatpandac" && passwordInput.text.toString() == "123456") {
                val newsIntent = Intent(this, NewsActivity::class.java)
                startActivity(newsIntent)
            }
        }
    }
}