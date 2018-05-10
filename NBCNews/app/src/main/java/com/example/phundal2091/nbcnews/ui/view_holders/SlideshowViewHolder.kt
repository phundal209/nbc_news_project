package com.example.phundal2091.nbcnews.ui.view_holders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.phundal2091.nbcnews.R

/**
 * Created by phundal2091 on 5/10/18.
 */
class SlideshowViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), IBaseViewHolder {
    override fun getHeadlineView(): TextView {
        return headline
    }

    override fun getTeaseView(): ImageView {
        return tease
    }

    val headline : TextView = itemView?.findViewById<TextView>(R.id.headline) as TextView
    val tease : ImageView = itemView?.findViewById<ImageView>(R.id.tease) as ImageView
    val viewSlideshow : TextView = itemView?.findViewById<TextView>(R.id.viewSlideshow) as TextView
}