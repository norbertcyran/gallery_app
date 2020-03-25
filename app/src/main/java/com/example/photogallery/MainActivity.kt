package com.example.photogallery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

const val GRID_COLUMNS = 3
val PHOTOS: Array<Image> = arrayOf(
    Image("photo 1", R.drawable.images),
    Image("photo 2", R.drawable.images2),
    Image("photo 3", R.drawable.images3),
    Image("photo 4", R.drawable.pronation_feet_runner_shoes),
    Image("photo 5", R.drawable.running),
    Image("photo 6", R.drawable.run_marathon_backward_crop),
    Image("photo 7", R.drawable.index),
    Image("photo 8", R.drawable.index2)
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewManager = GridLayoutManager(this, GRID_COLUMNS)
        val adapter = PhotoAdapter(this, PHOTOS)
        findViewById<RecyclerView>(R.id.photosGrid).apply {
            layoutManager = viewManager
            this.adapter = adapter
        }
    }
}
