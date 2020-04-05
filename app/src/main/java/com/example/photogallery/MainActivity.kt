package com.example.photogallery

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

const val GRID_COLUMNS = 3

const val IMAGES_PARCEL = "com.example.photogallery.IMAGE_PARCEL"
const val IMAGE_POSITION = "com.example.photogallery.IMAGE_POSITION"
const val READ_STORAGE_CODE = 99

class MainActivity : AppCompatActivity() {
    lateinit var photos: ArrayList<Image>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!hasPermissions()) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                READ_STORAGE_CODE
            )
            photos = ArrayList()
        } else {
            photos = loadImages(this)
        }
        val viewManager = GridLayoutManager(this, GRID_COLUMNS)
        val adapter = PhotoAdapter(photos) { _, pos ->
            maximizePhoto(pos)
        }
        findViewById<RecyclerView>(R.id.photosGrid).apply {
            layoutManager = viewManager
            this.adapter = adapter
        }
    }

    private fun maximizePhoto(position: Int) {
        val intent = Intent(this, PhotoFullscreenActivity::class.java).apply {
            putParcelableArrayListExtra(IMAGES_PARCEL, photos)
            putExtra(IMAGE_POSITION, position)
        }
        startActivity(intent)
    }

    private fun hasPermissions(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            READ_STORAGE_CODE -> {
                if (
                    grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                )
                    reload()
                else
                    Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT)
                        .show()
            }
            else -> {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
        }
    }

    fun reload() {
        finish()
        overridePendingTransition(0, 0)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }
}

fun loadImages(activity: Activity): ArrayList<Image> {
    val imagesList = ArrayList<Image>()

    val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    val projection = arrayOf(
        MediaStore.Images.ImageColumns._ID,
        MediaStore.Images.ImageColumns.DATE_ADDED
    )
    val sortOrder = "${MediaStore.Images.ImageColumns.DATE_ADDED} ASC"

    activity.contentResolver.query(uri, projection, null, null, sortOrder)
        ?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns._ID)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val contentUri = Uri.withAppendedPath(uri, id.toString())
                imagesList.add(Image(contentUri))
            }
        }
    return imagesList
}
