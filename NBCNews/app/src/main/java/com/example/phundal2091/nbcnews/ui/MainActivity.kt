package com.example.phundal2091.nbcnews.ui

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.phundal2091.nbcnews.R
import com.example.phundal2091.nbcnews.media_control.IMediaController
import com.example.phundal2091.nbcnews.media_control.MediaController
import com.example.phundal2091.nbcnews.network.ApiService
import com.example.phundal2091.nbcnews.network.IApiService
import com.example.phundal2091.nbcnews.network.IRetrofitProvider
import com.example.phundal2091.nbcnews.network.RetrofitProvider
import com.example.phundal2091.nbcnews.repository.INewsRepository
import com.example.phundal2091.nbcnews.repository.NewsRepository
import com.example.phundal2091.nbcnews.response.ItemsResponse
import com.example.phundal2091.nbcnews.ui.adapters.ArticleViewAdapter
import com.example.phundal2091.nbcnews.ui.adapters.SlideshowViewAdapter
import com.example.phundal2091.nbcnews.ui.adapters.VideoViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IMainViewHandler {
    var progressDialog : ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retrofitProvider : IRetrofitProvider = RetrofitProvider()
        val apiService : IApiService = ApiService(retrofitProvider)
        val newsRepository : INewsRepository = NewsRepository(apiService = apiService, context = this)
        bindNewsFromRepository(newsRepository)
    }

    fun bindNewsFromRepository(newsRepository: INewsRepository) {
        showProgress()
        newsRepository.callApi(object : NewsRepository.OnNetworkCallback {
            override fun onCallSuccess(itemsResponse: List<ItemsResponse>) {
                val articleList = newsRepository.getListByType(itemsResponse, NewsRepository.news_type.ARTICLE)
                val videoList = newsRepository.getListByType(itemsResponse, NewsRepository.news_type.VIDEO)
                val slideshowList = newsRepository.getListByType(itemsResponse, NewsRepository.news_type.SLIDESHOW)
                bindArticles(articleList)
                bindVideos(videoList)
                bindSlideshows(slideshowList)
                hideProgress()
            }

            override fun onCallFailed(throwable: Throwable) {
                Log.d(NewsRepository::class.java.simpleName, "error", throwable)
                hideProgress()
            }
        })
    }

    override fun showProgress() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(this, getString(R.string.api_loading_title), getString(R.string.api_loading_message))
        }
    }

    override fun hideProgress() {
        if(progressDialog != null) {
            progressDialog?.dismiss()
        }
    }

    override fun bindArticles(items: List<ItemsResponse>?) {
        val newsContentViewRecycler : ArticleViewAdapter = ArticleViewAdapter(this, items)
        val layoutManager : LinearLayoutManager = LinearLayoutManager(this)
        breakingNewsRecycler.adapter = newsContentViewRecycler
        breakingNewsRecycler.layoutManager = layoutManager
    }

    override fun bindVideos(items: List<ItemsResponse>?) {
        val mediaController : IMediaController = MediaController(this)
        val newsContentViewRecycler : VideoViewAdapter = VideoViewAdapter(this, items, mediaController)
        val layoutManager : LinearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        videoRecycler.adapter = newsContentViewRecycler
        videoRecycler.layoutManager = layoutManager
    }

    override fun bindSlideshows(items: List<ItemsResponse>?) {
        val mediaController : IMediaController = MediaController(this)
        val newsContentViewRecycler : SlideshowViewAdapter = SlideshowViewAdapter(this, items, mediaController)
        val layoutManager : LinearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        slideshowRecycler.adapter = newsContentViewRecycler
        slideshowRecycler.layoutManager = layoutManager
    }
}
