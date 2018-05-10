package com.example.phundal2091.nbcnews.ui.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.phundal2091.nbcnews.R
import com.example.phundal2091.nbcnews.media_control.IMediaController
import com.example.phundal2091.nbcnews.response.ItemsResponse
import com.example.phundal2091.nbcnews.ui.view_holders.SlideshowViewHolder

/**
 * Created by phundal2091 on 5/10/18.
 */
class SlideshowViewAdapter(val context: Context, val listOfItems : List<ItemsResponse>?, val mediaController: IMediaController) : RecyclerView.Adapter<SlideshowViewHolder>() {
    override fun onBindViewHolder(holder: SlideshowViewHolder?, position: Int) {
        var slideshowItem = listOfItems?.get(position)
        holder?.itemView?.setOnClickListener {
            mediaController.buildImageViewer(slideshowItem?._images, 0)
        }
        mediaController.bindImage(slideshowItem?._tease, holder?.tease)
        holder?.headline?.text = slideshowItem?._headline
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SlideshowViewHolder {
        var view : View = LayoutInflater.from(parent?.context).inflate(R.layout.news_slideshow_layout, parent, false)
        return SlideshowViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (listOfItems != null) return listOfItems.size
        return 0
    }
}