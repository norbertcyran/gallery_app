package com.example.photogallery

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

const val GRID_COLUMNS = 3

val PHOTOS: ArrayList<Image> = ArrayList<Image>().apply {
    add(Image("photo 1", R.drawable.images))
    add(Image("photo 2", R.drawable.images2))
    add(Image("photo 3", R.drawable.images3))
    add(Image("photo 4", R.drawable.pronation_feet_runner_shoes))
    add(Image("photo 5", R.drawable.running))
    add(Image("photo 6", R.drawable.run_marathon_backward_crop))
    add(Image("photo 7", R.drawable.index))
    add(Image("photo 8", R.drawable.index2))
}

const val IMAGE_POSITION = "com.example.photogallery.IMAGE_POSITION"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewManager = GridLayoutManager(this, GRID_COLUMNS)
        val adapter = PhotoAdapter(PHOTOS) { _, pos ->
            maximizePhoto(pos)
        }
        findViewById<RecyclerView>(R.id.photosGrid).apply {
            layoutManager = viewManager
            this.adapter = adapter
        }
    }

    private fun maximizePhoto(position: Int) {
        val intent = Intent(this, PhotoFullscreenActivity::class.java).apply {
            putExtra(IMAGE_POSITION, position)
        }
        startActivity(intent)
    }
}
