package com.example.phundal2091.nbcnews.ui

import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.phundal2091.nbcnews.R
import com.example.phundal2091.nbcnews.response.ItemsResponse
import com.example.phundal2091.nbcnews.ui.view_holders.ArticleViewHolder
import com.example.phundal2091.nbcnews.ui.view_holders.SlideshowViewHolder
import com.example.phundal2091.nbcnews.ui.view_holders.VideoViewHolder

/**
 * Created by phundal2091 on 5/9/18.
 */
class NewsContentViewRecycler(val listOfItems : List<ItemsResponse>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        val ARTICLE_NEWS_ITEM = "article"
        val VIDEO_NEWS_ITEM = "slideshow"
        val SLIDESHOW_NEWS_ITEM = "video"

        val ARTICLE_NEWS_TYPE = 1
        val VIDEO_NEWS_TYPE = 2
        val SLIDESHOW_NEWS_TYPE = 3
    }

    enum class RowType(val viewTypeId : Int, val resourceId : Int) {
        article_type(viewTypeId = ARTICLE_NEWS_TYPE, resourceId = R.layout.news_article_layout),
        video_type(viewTypeId = VIDEO_NEWS_TYPE, resourceId = R.layout.news_video_layout),
        slideshow_type(viewTypeId = SLIDESHOW_NEWS_TYPE, resourceId = R.layout.news_slideshow_layout);


        companion object
        {
            var mappedTypes: HashMap<Int, RowType> = hashMapOf()
            fun createMap() {
                mappedTypes.put(ARTICLE_NEWS_TYPE, article_type)
                mappedTypes.put(VIDEO_NEWS_TYPE, video_type)
                mappedTypes.put(SLIDESHOW_NEWS_TYPE, slideshow_type)
            }

            fun getRowTypeByViewType(viewType : Int) : RowType? {
                createMap()
                return mappedTypes[viewType]
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        var rowType : RowType? = RowType.getRowTypeByViewType(viewType)
        val view = LayoutInflater.from(parent?.context).inflate(rowType?.resourceId!!, parent, false)

        when (viewType) {
            ARTICLE_NEWS_TYPE -> return ArticleViewHolder(view)
            VIDEO_NEWS_TYPE -> return VideoViewHolder(view)
            SLIDESHOW_NEWS_TYPE -> return SlideshowViewHolder(view)
        }
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (listOfItems != null) {
            return listOfItems.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val newsItem : ItemsResponse = listOfItems?.get(position)!!

        when (holder?.itemViewType) {
            ARTICLE_NEWS_TYPE -> Log.d(NewsContentViewRecycler::class.java.simpleName, "article item")
            VIDEO_NEWS_TYPE -> Log.d(NewsContentViewRecycler::class.java.simpleName, "video item")
            SLIDESHOW_NEWS_TYPE -> Log.d(NewsContentViewRecycler::class.java.simpleName, "slideshow item")
        }
    }

    override fun getItemViewType(position: Int): Int {
        val itemResponse = listOfItems?.get(position)
        if (itemResponse?._type?.equals(ARTICLE_NEWS_ITEM)!!) {
            return ARTICLE_NEWS_TYPE
        } else if (itemResponse._type?.equals(VIDEO_NEWS_ITEM)!!) {
            return VIDEO_NEWS_TYPE
        } else if (itemResponse._type?.equals(SLIDESHOW_NEWS_ITEM)!!) {
            return SLIDESHOW_NEWS_TYPE
        }
        return ARTICLE_NEWS_TYPE
    }

}

