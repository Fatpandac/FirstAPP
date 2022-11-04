package com.example.fistapp.example.fragment

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fistapp.R

class MyFragmentActivity : AppCompatActivity(), LeftFragment.Callbacks {

    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_fragment)
        textView = findViewById(R.id.tv)
    }

    override fun send(s: String?) {
        Log.e("S", s!!)
        textView?.text = s
    }
}