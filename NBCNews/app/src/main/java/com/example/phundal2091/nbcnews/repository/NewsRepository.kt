package com.example.phundal2091.nbcnews.repository

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.example.phundal2091.nbcnews.R
import com.example.phundal2091.nbcnews.network.ApiService
import com.example.phundal2091.nbcnews.network.IApiService
import com.example.phundal2091.nbcnews.response.ItemsResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by phundal2091 on 5/10/18.
 */
class NewsRepository(val apiService: IApiService, val context: Context) : INewsRepository {

    interface OnNetworkCallback {
        fun onCallSuccess(itemsResponse: List<ItemsResponse>)
        fun onCallFailed(throwable: Throwable)
    }

    override fun callApi(onNetworkCallback: OnNetworkCallback) {
        apiService.getCuratedContentAsync().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .onErrorReturn { throwable ->
                    onNetworkCallback.onCallFailed(throwable)
                    null
                }
                .subscribe {
                    onNetworkCallback.onCallSuccess(it)
                }
    }

    override fun getListByType(itemsResponses : List<ItemsResponse>, type : news_type) : List<ItemsResponse> {
        val listOfTypedResponse = mutableListOf<ItemsResponse>()
        itemsResponses
                .filter { it._type?.equals(type.type)!! }
                .forEach { listOfTypedResponse.add(it) }
        return listOfTypedResponse
    }

    enum class news_type(val type : String) {
        ARTICLE("article"),
        VIDEO("video"),
        SLIDESHOW("slideshow")


    }
}