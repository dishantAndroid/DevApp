package com.practice.devapp.service

import android.app.job.JobParameters
import android.app.job.JobService
import android.media.MediaPlayer
import android.util.Log
import com.practice.devapp.R

class MusicPlayerJobService : JobService() {

    companion object {
        private const val JOB_ID = 1
        private lateinit var mediaPlayer: MediaPlayer
        var isRunning = false
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.healthkosreminder)
        mediaPlayer.isLooping = true
    }

    override fun onStartJob(jobParameters: JobParameters?): Boolean {

        Runnable {
            doBackgroundWork()
        }.run()

        return true
    }

    fun doBackgroundWork() {
        isRunning = true

        while (isRunning) {
            Log.d("MusicPlayerJobService", "job intent service is Running..")
            Thread.sleep(1000)
            mediaPlayer.start()
        }

    }

    override fun onStopJob(jobParameters: JobParameters?): Boolean {

        return true
    }

}