package com.example.phundal2091.nbcnews.ui.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.phundal2091.nbcnews.R
import com.example.phundal2091.nbcnews.media_control.IMediaController
import com.example.phundal2091.nbcnews.response.ItemsResponse
import com.example.phundal2091.nbcnews.ui.view_holders.ArticleViewHolder

/**
 * Created by phundal2091 on 5/10/18.
 */
class ArticleViewAdapter(val context: Context, val listOfItems : List<ItemsResponse>?) : RecyclerView.Adapter<ArticleViewHolder>() {

    override fun getItemCount(): Int {
        if (listOfItems != null) return listOfItems.size
        return 0
    }

    override fun onBindViewHolder(holder: ArticleViewHolder?, position: Int) {
        var articleItem = listOfItems?.get(position)
        holder?.headline?.text = articleItem?._headline
        holder?.itemView?.setOnClickListener {
            var intent : Intent = Intent(Intent.ACTION_VIEW);
                intent.data = Uri.parse(articleItem?._url)
                context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ArticleViewHolder {
        var view : View = LayoutInflater.from(parent?.context).inflate(R.layout.news_article_layout, parent, false)
        return ArticleViewHolder(view)
    }
}