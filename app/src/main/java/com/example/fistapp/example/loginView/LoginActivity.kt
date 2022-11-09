package com.example.fistapp.example.loginView

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.fistapp.R
import com.example.fistapp.example.listView.HelloActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btn = findViewById<Button>(R.id.login)
        btn.setOnClickListener {
            val accountInput = findViewById<EditText>(R.id.account)
            val passwordInput = findViewById<EditText>(R.id.password)
            Log.e("Login:", "${passwordInput.text} ${accountInput.text}")
            if (accountInput.text.toString() == "i@fatpandac.com" && passwordInput.text.toString() == "123456") {
                val newsIntent = Intent(this, HelloActivity::class.java)
                startActivity(newsIntent)
            }
        }
    }
}