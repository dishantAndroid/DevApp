package com.practice.devapp.service

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.util.Log
import androidx.core.app.JobIntentService
import com.practice.devapp.R

class MusicPlayerJobIntentService : JobIntentService() {

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.healthkosreminder)
        mediaPlayer.isLooping = true
    }

    companion object {
        private const val JOB_ID = 1
        private lateinit var mediaPlayer: MediaPlayer
        var isRunning = false
        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, MusicPlayerJobIntentService::class.java, JOB_ID, intent)
        }
    }

    override fun onHandleWork(intent: Intent) {
        try {
            isRunning = true

            while (isRunning) {
                Log.d("MusicPlayerJobIntentService", "job intent service is Running..")
                for (i in 0..5) {
                    Thread.sleep(1000)
                    mediaPlayer.start()
                }
                isRunning = false

            }
            stopSelf()
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
        }

    }

    override fun onStopCurrentWork(): Boolean {
        return true;
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }
}