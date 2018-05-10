package com.example.phundal2091.nbcnews.ui

import android.os.Bundle
import com.example.phundal2091.nbcnews.network.IApiService
import com.example.phundal2091.nbcnews.response.ItemsResponse

/**
 * Created by phundal2091 on 5/9/18.
 */
interface IMainViewHandler {
    fun callApi(apiService: IApiService, savedInstanceState: Bundle?)
    fun bindRecyclerView(items : List<ItemsResponse>?)
}