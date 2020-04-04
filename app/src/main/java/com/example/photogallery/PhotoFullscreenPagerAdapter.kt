package com.example.photogallery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide

class PhotoFullscreenPagerAdapter(private val images: ArrayList<Image>) : PagerAdapter() {
    private lateinit var context: Context

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj as View
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        context = container.context

        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_image_fullscreen, container, false)

        val image = images[position]

        Glide.with(context).load(image.uri).into(view as ImageView)
        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }
}
