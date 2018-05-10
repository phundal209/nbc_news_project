package com.example.phundal2091.nbcnews.media_control

import android.content.Context
import android.widget.ImageView
import com.example.phundal2091.nbcnews.R
import com.example.phundal2091.nbcnews.response.ImagesResponse
import com.squareup.picasso.Picasso
import com.stfalcon.frescoimageviewer.ImageViewer

/**
 * Created by phundal2091 on 5/10/18.
 */
class MediaController(val context : Context) : IMediaController {
    override fun buildImageViewer(data: List<ImagesResponse>?, startPosition: Int?) {
        if(startPosition != null) {
            ImageViewer.Builder(context, data)
                    .setStartPosition(startPosition)
                    .setFormatter(ImageViewer.Formatter<ImagesResponse>(ImagesResponse::_url))
                    .show()
        }
    }

    override fun bindImage(url : String?, imageView: ImageView?) {
        Picasso.get().load(url).placeholder(R.color.placeholderColor).into(imageView)
    }
}