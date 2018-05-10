//package com.example.phundal2091.nbcnews.ui
//
//import android.content.Context
//import android.content.Intent
//import android.media.session.MediaController
//import android.net.Uri
//import android.support.v7.widget.RecyclerView
//import android.text.Layout
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.example.phundal2091.nbcnews.R
//import com.example.phundal2091.nbcnews.media_control.IMediaController
//import com.example.phundal2091.nbcnews.response.ItemsResponse
//import com.example.phundal2091.nbcnews.ui.view_holders.ArticleViewHolder
//import com.example.phundal2091.nbcnews.ui.view_holders.IBaseViewHolder
//import com.example.phundal2091.nbcnews.ui.view_holders.SlideshowViewHolder
//import com.example.phundal2091.nbcnews.ui.view_holders.VideoViewHolder
//
///**
// * Created by phundal2091 on 5/9/18.
// */
//class NewsContentViewRecycler(val context: Context, val listOfItems : List<ItemsResponse>?, val mediaController: IMediaController) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    companion object {
//        val ARTICLE_NEWS_ITEM = "article"
//        val VIDEO_NEWS_ITEM = "video"
//        val SLIDESHOW_NEWS_ITEM = "slideshow"
//
//        val ARTICLE_NEWS_TYPE = 1
//        val VIDEO_NEWS_TYPE = 2
//        val SLIDESHOW_NEWS_TYPE = 3
//    }
//
//    enum class RowType(val viewTypeId : Int, val resourceId : Int) {
//        article_type(viewTypeId = ARTICLE_NEWS_TYPE, resourceId = R.layout.news_article_layout),
//        video_type(viewTypeId = VIDEO_NEWS_TYPE, resourceId = R.layout.news_video_layout),
//        slideshow_type(viewTypeId = SLIDESHOW_NEWS_TYPE, resourceId = R.layout.news_slideshow_layout);
//
//
//        companion object
//        {
//            var mappedTypes: HashMap<Int, RowType> = hashMapOf()
//            fun createMap() {
//                mappedTypes.put(ARTICLE_NEWS_TYPE, article_type)
//                mappedTypes.put(VIDEO_NEWS_TYPE, video_type)
//                mappedTypes.put(SLIDESHOW_NEWS_TYPE, slideshow_type)
//            }
//
//            fun getRowTypeByViewType(viewType : Int) : RowType? {
//                createMap()
//                return mappedTypes[viewType]
//            }
//        }
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
//        var rowType : RowType? = RowType.getRowTypeByViewType(viewType)
//        val view = LayoutInflater.from(parent?.context).inflate(rowType?.resourceId!!, parent, false)
//
//        when (viewType) {
//            ARTICLE_NEWS_TYPE -> return ArticleViewHolder(view)
//            VIDEO_NEWS_TYPE -> return VideoViewHolder(view)
//            SLIDESHOW_NEWS_TYPE -> return SlideshowViewHolder(view)
//        }
//        return ArticleViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        if (listOfItems != null) {
//            return listOfItems.size
//        }
//        return 0
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
//        val newsItem : ItemsResponse = listOfItems?.get(position)!!
//        when (holder?.itemViewType) {
//            ARTICLE_NEWS_TYPE -> bindArticle(newsItem, holder)
//            VIDEO_NEWS_TYPE -> bindVideo(newsItem, holder)
//            SLIDESHOW_NEWS_TYPE -> bindSlideshow(newsItem, holder)
//        }
//    }
//
//    fun bindHeadlineAndTease(newsItem: ItemsResponse, holder : IBaseViewHolder?) {
//        if (holder != null) {
//            if (newsItem._headline != null) {
//                holder.getHeadlineView().text = newsItem._headline
//            }
//            if (newsItem._tease != null) {
//                mediaController.bindImage(newsItem._tease, holder.getTeaseView())
//            }
//        }
//    }
//
//    fun bindArticle(newsItem : ItemsResponse, holder: RecyclerView.ViewHolder?) {
//        if (holder != null) {
//            val articleHolder = holder as ArticleViewHolder
//            bindHeadlineAndTease(newsItem, articleHolder)
//
//            holder.itemView.setOnClickListener {
//                var intent : Intent = Intent(Intent.ACTION_VIEW);
//                intent.data = Uri.parse(newsItem._url)
//                context.startActivity(intent)
//            }
//        }
//    }
//
//    fun bindVideo(newsItem: ItemsResponse, holder: RecyclerView.ViewHolder?) {
//        if (holder != null) {
//            val videoViewHolder = holder as VideoViewHolder
//            bindHeadlineAndTease(newsItem, videoViewHolder)
//        }
//    }
//
//    fun bindSlideshow(newsItem: ItemsResponse, holder: RecyclerView.ViewHolder?) {
//        if (holder != null) {
//            val slideshowViewHolder = holder as SlideshowViewHolder
//            bindHeadlineAndTease(newsItem, slideshowViewHolder)
//
//            slideshowViewHolder.itemView.setOnClickListener {
//                mediaController.buildImageViewer(newsItem._images, 0)
//            }
//        }
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        val itemResponse = listOfItems?.get(position)
//        if (itemResponse?._type?.equals(ARTICLE_NEWS_ITEM)!!) {
//            return ARTICLE_NEWS_TYPE
//        } else if (itemResponse._type?.equals(VIDEO_NEWS_ITEM)!!) {
//            return VIDEO_NEWS_TYPE
//        } else if (itemResponse._type?.equals(SLIDESHOW_NEWS_ITEM)!!) {
//            return SLIDESHOW_NEWS_TYPE
//        }
//        return ARTICLE_NEWS_TYPE
//    }
//
//}
//
