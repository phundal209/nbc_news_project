package com.example.phundal2091.nbcnews.ui.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.phundal2091.nbcnews.R
import com.example.phundal2091.nbcnews.media_control.IMediaController
import com.example.phundal2091.nbcnews.response.ItemsResponse
import com.example.phundal2091.nbcnews.ui.view_holders.VideoViewHolder

/**
 * Created by phundal2091 on 5/10/18.
 */
class VideoViewAdapter(val context: Context, val listOfItems : List<ItemsResponse>?, val mediaController: IMediaController) : RecyclerView.Adapter<VideoViewHolder>(){
    override fun getItemCount(): Int {
        if (listOfItems != null) return listOfItems.size
        return 0
    }

    override fun onBindViewHolder(holder: VideoViewHolder?, position: Int) {
        var videoItem = listOfItems?.get(position)
        holder?.description?.text = videoItem?._headline
        holder?.duration?.text = videoItem?._duration
        mediaController.bindImage(videoItem?._tease, holder?.videoImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VideoViewHolder {
        var view : View = LayoutInflater.from(parent?.context).inflate(R.layout.news_video_layout, parent, false)
        return VideoViewHolder(view)
    }
}