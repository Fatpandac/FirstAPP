package com.example.fistapp.example.services

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log

private const val PLAY_AUDIO_ACTIVITY = "PLAY_AUDIO_ACTIVITY"

class BackgroundPlay : Service() {
    private lateinit var mPlayer: MediaPlayer
    private var isRelease = true

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        when (intent.action) {
            PLAY_AUDIO_ACTIVITY -> {
                when (intent.getStringExtra("flag")) {
                    "play" -> {
                        if (isRelease) {
                            mPlayer = MediaPlayer().apply {
                                setAudioAttributes(
                                    AudioAttributes.Builder()
                                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                        .build()
                                )
                                setDataSource(intent.getStringExtra("play_source"))
                                prepare()
                                start()
                            }
                            isRelease = false
                        } else {
                            mPlayer.start()
                        }
                    }
                    "pause" -> {
                        mPlayer.pause()
                    }
                    "stop" -> {
                        mPlayer.reset()
                        mPlayer.release()
                        isRelease = true
                    }
                }
            }
            else -> {
                Log.e("action not define", intent.action.toString())
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

}

