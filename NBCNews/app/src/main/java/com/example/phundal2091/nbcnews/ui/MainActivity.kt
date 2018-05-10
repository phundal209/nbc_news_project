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
import com.example.phundal2091.nbcnews.response.ItemsResponse
import com.example.phundal2091.nbcnews.ui.adapters.ArticleViewAdapter
import com.example.phundal2091.nbcnews.ui.adapters.SlideshowViewAdapter
import com.example.phundal2091.nbcnews.ui.adapters.VideoViewAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IMainViewHandler {
    var progressDialog : ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retrofitProvider : IRetrofitProvider = RetrofitProvider()
        val apiService : IApiService = ApiService(retrofitProvider)
        callApi(apiService, savedInstanceState)
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


    override fun callApi(apiService: IApiService, savedInstanceState: Bundle?) {
        showProgress()
        apiService.getCuratedContentAsync().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .onErrorReturn { throwable ->
                    Log.d(ApiService::class.java.simpleName, getString(R.string.api_error_message), throwable)
                    hideProgress()
                    null
                }
                .subscribe {
                    bindArticles(getListByType(it, "article"))
                    bindVideos(getListByType(it, "video"))
                    bindSlideshows(getListByType(it, "slideshow"))

                    hideProgress()
                }
    }


    fun getListByType(itemsResponses : List<ItemsResponse>, type : String) : List<ItemsResponse> {
        val listOfTypedResponse = mutableListOf<ItemsResponse>()
        itemsResponses
                .filter { it._type?.equals(type)!! }
                .forEach { listOfTypedResponse.add(it) }
        return listOfTypedResponse
    }
}
