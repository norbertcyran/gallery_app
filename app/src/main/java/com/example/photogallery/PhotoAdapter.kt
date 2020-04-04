package com.example.photogallery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PhotoAdapter(
    private val photos: ArrayList<Image>,
    private val clickListener: (View, Int) -> Unit
) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(imageView: View) : RecyclerView.ViewHolder(imageView) {
        private val imageView: ImageView = itemView.findViewById(R.id.galleryImage)
        fun bind(image: Image) {
            Glide.with(context).load(image.uri).into(imageView)

            imageView.setOnClickListener {
                clickListener(it, adapterPosition)
            }
        }
    }

    override fun getItemCount(): Int = photos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val imageView = inflater.inflate(R.layout.item_image, parent, false)
        return ViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(photos[position])
    }
}
