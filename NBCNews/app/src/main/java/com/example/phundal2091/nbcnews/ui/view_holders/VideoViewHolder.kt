package com.example.phundal2091.nbcnews.ui.view_holders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.phundal2091.nbcnews.R

/**
 * Created by phundal2091 on 5/10/18.
 */
class VideoViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    val videoImage : ImageView = itemView?.findViewById<ImageView>(R.id.videoImage) as ImageView
    val description : TextView = itemView?.findViewById<TextView>(R.id.description) as TextView
    val duration : TextView = itemView?.findViewById<TextView>(R.id.duration) as TextView
}