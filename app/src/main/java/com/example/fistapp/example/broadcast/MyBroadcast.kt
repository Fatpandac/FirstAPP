package com.example.fistapp.example.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.TextView

class MyBroadcast(val textView: TextView) : BroadcastReceiver() {
    private var time = 0

    override fun onReceive(context: Context?, intent: Intent) {
        if (intent.getBooleanExtra("isCancel", false)) {
            time = 0
        } else {
            time++
            textView.text = time.toString()
        }
    }
}