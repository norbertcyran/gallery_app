package com.example.photogallery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class PhotoAdapter(private val context: Context, private val photos: Array<Image>) :
        RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    class ViewHolder(imageView: View) : RecyclerView.ViewHolder(imageView) {
        private val imageView: ImageView = itemView.findViewById(R.id.galleryImage)
        fun bind(image: Image) {
            imageView.setImageResource(image.resId)
        }
    }

    override fun getItemCount(): Int = photos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val imageView = inflater.inflate(R.layout.item_image, parent, false)
        return ViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(photos[position])
    }
}
