package com.example.phundal2091.nbcnews.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.phundal2091.nbcnews.R
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
        var newsContentViewRecycler : NewsContentViewRecycler = NewsContentViewRecycler(items)
        var layoutManager : LinearLayoutManager = LinearLayoutManager(this)
        // todo use synthetic binding, don't know why it won't find it right now?
        var recyclerView : RecyclerView = findViewById(R.id.recyclerView)
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
                    if (it != null
                            && it[0]._items != null) {
                        bindRecyclerView(it[0]._items)
                    }
                }
    }
}
