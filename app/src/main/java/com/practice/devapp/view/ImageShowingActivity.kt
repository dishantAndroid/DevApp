package com.practice.devapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.practice.devapp.databinding.ActivityImageShowingBinding
import com.practice.devapp.service.MusicPlayerService

class ImageShowingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val imageShowingBinding = ActivityImageShowingBinding.inflate(layoutInflater)
        setContentView(imageShowingBinding.root)


        intent?.apply {
            var text = extras!!.getString("value", "")

            imageShowingBinding.tvText.text = text

//            imageShowingBinding.ivImage.setImageBitmap(image)

        }

        imageShowingBinding.tvText.setOnClickListener {
            stopService(Intent(this, MusicPlayerService::class.java))
        }
    }
}