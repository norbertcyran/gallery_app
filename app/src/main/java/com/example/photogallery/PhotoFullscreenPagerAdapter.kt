package com.example.photogallery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.item_image_fullscreen.view.*

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

        view.fullscreenImage.setImageResource(image.resId)
        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }
}
