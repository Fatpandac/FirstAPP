package com.example.fistapp.example.loginView

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.fistapp.R
import com.example.fistapp.example.listView.HelloActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val accountInput = findViewById<EditText>(R.id.account)
        val passwordInput = findViewById<EditText>(R.id.password)
        val rememberCheckBox = findViewById<CheckBox>(R.id.rememberCheckBox)
        val btn = findViewById<Button>(R.id.login)
        val sharedPreferences = getSharedPreferences("account", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val account = sharedPreferences.getString("account", "")
        val password = sharedPreferences.getString("password", "")
        accountInput.setText(account)
        passwordInput.setText(password)



        btn.setOnClickListener {
            Log.e("Login:", "${passwordInput.text} ${accountInput.text}")
            if (accountInput.text.toString() == "i@fatpandac.com" && passwordInput.text.toString() == "123456") {
                if (rememberCheckBox.isChecked) {
                    editor.putString("account", accountInput.text.toString())
                    editor.putString("password", passwordInput.text.toString())
                } else {
                    editor.putString("account", "")
                    editor.putString("password", "")
                }
                editor.apply()
                val newsIntent = Intent(this, HelloActivity::class.java)
                startActivity(newsIntent)
            }
        }
    }
}