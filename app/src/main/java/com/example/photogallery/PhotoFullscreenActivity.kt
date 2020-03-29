package com.example.photogallery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

class PhotoFullscreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_fullscreen)

        val position = intent.extras!!.get(IMAGE_POSITION)
        val adapter = PhotoFullscreenPagerAdapter(PHOTOS)

        findViewById<ViewPager>(R.id.viewPager).apply {
            setAdapter(adapter)
            currentItem = position as Int
        }
    }
}
