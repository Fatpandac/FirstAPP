package com.example.fistapp.example.services

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.fistapp.R

class PlayAudioActivity : AppCompatActivity(), View.OnClickListener {
    private val musicUrl =
        "https://m701.music.126.net/20221123152420/060dd6215f881379050252c0df72fbcf/jdyyaac/0658/065d/060f/12acece15c17343e27621e0c45f9cfc3.m4a"
    private lateinit var btnPlay: Button
    private lateinit var btnPause: Button
    private lateinit var btnStop: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_audio)
        bindButton()
        intent.setClass(this, BackgroundPlay::class.java)
        intent.action = "PLAY_AUDIO_ACTIVITY"
    }

    private fun bindButton() {
        btnPlay = this.findViewById(R.id.play)
        btnPause = this.findViewById(R.id.pause)
        btnStop = this.findViewById(R.id.stop)

        btnPlay.setOnClickListener(this)
        btnPause.setOnClickListener(this)
        btnStop.setOnClickListener(this)

        btnPlay.isEnabled = true
        btnPause.isEnabled = false
        btnStop.isEnabled = false
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.play -> {
                intent.putExtra("flag", "play")
                intent.putExtra("play_source", musicUrl)

                btnPlay.isEnabled = false
                btnStop.isEnabled = true
                btnPause.isEnabled = true
            }
            R.id.pause -> {
                intent.putExtra("flag", "pause")

                btnPlay.isEnabled = true
                btnStop.isEnabled = false
                btnPause.isEnabled = true
            }
            R.id.stop -> {
                intent.putExtra("flag", "stop")

                btnPlay.isEnabled = true
                btnStop.isEnabled = false
                btnPause.isEnabled = false
            }
        }
        startService(intent)
    }
}
