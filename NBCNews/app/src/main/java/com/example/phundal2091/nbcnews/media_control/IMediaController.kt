package com.example.phundal2091.nbcnews.media_control

import android.content.Context
import android.widget.ImageView
import android.widget.VideoView
import com.example.phundal2091.nbcnews.response.ImagesResponse

/**
 * Created by phundal2091 on 5/10/18.
 */
interface IMediaController {
    fun bindImage(url : String?, imageView: ImageView?)
    fun buildImageViewer(data : List<ImagesResponse>?, startPosition : Int?)
    fun playVideo(url : String?, videoView: VideoView)
}