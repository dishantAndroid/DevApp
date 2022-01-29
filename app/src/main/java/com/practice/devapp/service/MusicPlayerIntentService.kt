package com.practice.devapp.service

import android.app.IntentService
import android.content.Intent
import android.media.MediaPlayer
import android.util.Log
import com.practice.devapp.R

class MusicPlayerIntentService : IntentService("MusicPlayerIntentService") {

    init {
        instance = this

    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.healthkosreminder)
        mediaPlayer.isLooping = true
    }


    companion object {
        private lateinit var instance: MusicPlayerIntentService
        private lateinit var mediaPlayer: MediaPlayer
        var isRunning = false

        const val TAG = "MusicPlayerIntentService"


        fun stopMyIntentService() {
            Log.d(TAG, "Service stopped..")
            instance.stopSelf()
        }

    }


    override fun onHandleIntent(p0: Intent?) {

        try {
            isRunning = true

            while (isRunning) {
                Log.d(TAG, "Service is Running..")
                Thread.sleep(1000)
                mediaPlayer.start()
            }
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }

}