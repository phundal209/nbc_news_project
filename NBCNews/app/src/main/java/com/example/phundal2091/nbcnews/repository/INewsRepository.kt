package com.example.phundal2091.nbcnews.repository

import com.example.phundal2091.nbcnews.network.IApiService
import com.example.phundal2091.nbcnews.response.ItemsResponse

/**
 * Created by phundal2091 on 5/10/18.
 */
interface INewsRepository {
    fun callApi(onNetworkCallback: NewsRepository.OnNetworkCallback)
    fun getListByType(itemsResponses : List<ItemsResponse>, type : NewsRepository.news_type) : List<ItemsResponse>
}