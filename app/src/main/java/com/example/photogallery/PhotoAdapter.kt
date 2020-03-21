package com.example.photogallery

import android.content.Context
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class PhotoAdapter(private val context: Context, private val photos: Array<String>) :
        RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    class ViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)

    override fun getItemCount(): Int = photos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val textView = TextView(context)
        return ViewHolder(textView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = photos[position]
    }
}