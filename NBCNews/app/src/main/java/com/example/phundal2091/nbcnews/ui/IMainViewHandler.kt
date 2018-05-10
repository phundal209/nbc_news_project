package com.example.phundal2091.nbcnews.ui

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.example.phundal2091.nbcnews.network.IApiService
import com.example.phundal2091.nbcnews.response.ItemsResponse

/**
 * Created by phundal2091 on 5/9/18.
 */
interface IMainViewHandler {
    fun callApi(apiService: IApiService, savedInstanceState: Bundle?)
    fun bindArticles(items : List<ItemsResponse>?)
    fun bindVideos(items : List<ItemsResponse>?)
    fun bindSlideshows(items : List<ItemsResponse>?)
    fun showProgress()
    fun hideProgress()
}