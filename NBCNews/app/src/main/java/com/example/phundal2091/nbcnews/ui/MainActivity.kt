package com.example.phundal2091.nbcnews.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import com.example.phundal2091.nbcnews.R
import com.example.phundal2091.nbcnews.constants.Constants
import com.example.phundal2091.nbcnews.media_control.IMediaController
import com.example.phundal2091.nbcnews.media_control.MediaController
import com.example.phundal2091.nbcnews.network.ApiService
import com.example.phundal2091.nbcnews.network.IApiService
import com.example.phundal2091.nbcnews.network.IRetrofitProvider
import com.example.phundal2091.nbcnews.network.RetrofitProvider
import com.example.phundal2091.nbcnews.response.ItemsResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity(), IMainViewHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retrofitProvider : IRetrofitProvider = RetrofitProvider()
        val apiService : IApiService = ApiService(retrofitProvider)
        callApi(apiService, savedInstanceState)
    }

    override fun bindRecyclerView(items: List<ItemsResponse>?) {
        val mediaController : IMediaController = MediaController(this)
        val newsContentViewRecycler : NewsContentViewRecycler = NewsContentViewRecycler(this, items, mediaController)
        val layoutManager : StaggeredGridLayoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = newsContentViewRecycler
        recyclerView.layoutManager = layoutManager

    }

    override fun callApi(apiService: IApiService, savedInstanceState: Bundle?) {
        apiService.getCuratedContentAsync().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .onErrorReturn { throwable ->
                    Log.d(ApiService::class.java.simpleName, getString(R.string.api_error_message), throwable)
                    null
                }
                .subscribe {
                    bindRecyclerView(it)
                }
    }
}
