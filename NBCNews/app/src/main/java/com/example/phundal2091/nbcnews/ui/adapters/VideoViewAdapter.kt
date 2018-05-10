package com.example.phundal2091.nbcnews.ui.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.phundal2091.nbcnews.R
import com.example.phundal2091.nbcnews.constants.Constants.Companion.url_key_extra
import com.example.phundal2091.nbcnews.constants.Constants.Companion.video_summary_key_extra
import com.example.phundal2091.nbcnews.media_control.IMediaController
import com.example.phundal2091.nbcnews.response.ItemsResponse
import com.example.phundal2091.nbcnews.ui.VideoActivity
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
        val videoItem = listOfItems?.get(position)
        holder?.description?.text = videoItem?._headline
        holder?.duration?.text = videoItem?._duration
        mediaController.bindImage(videoItem?._tease, holder?.videoImage)
        holder?.itemView?.setOnClickListener {
            val urlOfVideo = videoItem?._preview
            val summary = videoItem?._summary
            val intent : Intent = Intent(context, VideoActivity::class.java)
            intent.putExtra(url_key_extra, urlOfVideo)
            intent.putExtra(video_summary_key_extra, summary)
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VideoViewHolder {
        var view : View = LayoutInflater.from(parent?.context).inflate(R.layout.news_video_layout, parent, false)
        return VideoViewHolder(view)
    }
}