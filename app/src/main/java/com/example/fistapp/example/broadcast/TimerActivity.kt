package com.example.fistapp.example.broadcast

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fistapp.R
import java.util.*
import kotlin.concurrent.fixedRateTimer

class TimerActivity : AppCompatActivity() {
    lateinit var timer: Timer
    lateinit var startButton: Button
    lateinit var stopButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        val myBroadcast = MyBroadcast(findViewById(R.id.time))
        val intentFilter = IntentFilter()
        intentFilter.addAction("com.example.fistapp.example.broadcast")
        registerReceiver(myBroadcast, intentFilter)

        startButton = findViewById(R.id.timer_start)
        stopButton = findViewById(R.id.timer_stop)

        startButton.setOnClickListener {
            startTimer()
            startButton.isEnabled = false
            stopButton.isEnabled = true
        }
        stopButton.setOnClickListener {
            stopTimer()
            startButton.isEnabled = true
            stopButton.isEnabled = false
        }
    }

    private fun startTimer() {
        timer = fixedRateTimer("", false, 0, 1000) {
            val intent = Intent("com.example.fistapp.example.broadcast")
            sendBroadcast(intent)
        }
    }

    private fun stopTimer() {
        timer.cancel()
        val intent = Intent("com.example.fistapp.example.broadcast")
        intent.putExtra("isCancel", true)
        sendBroadcast(intent)
        findViewById<TextView>(R.id.time).text = "0"
    }

    override fun onDestroy() {
        timer.cancel()
        super.onDestroy()
    }
}