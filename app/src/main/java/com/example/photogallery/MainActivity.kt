package com.example.photogallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

const val GRID_COLUMNS = 3

class MainActivity : AppCompatActivity() {
    private val photos: Array<String> = arrayOf(
        "photo 1",
        "photo 2",
        "photo 3",
        "photo 4",
        "photo 5"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewManager = GridLayoutManager(this, GRID_COLUMNS)
        val adapter = PhotoAdapter(this, photos)
        findViewById<RecyclerView>(R.id.photosGrid).apply {
            layoutManager = viewManager
            this.adapter = adapter
        }
    }

}
