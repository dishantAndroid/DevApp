package com.practice.devapp.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings

class MusicPlayerService : Service() {

    lateinit var mediaPlayer: MediaPlayer

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)
        mediaPlayer.isLooping = true
        mediaPlayer.start()

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }
}