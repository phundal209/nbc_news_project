package com.example.phundal2091.nbcnews.network

import com.example.phundal2091.nbcnews.constants.Constants
import com.example.phundal2091.nbcnews.response.CuratedContentResponse
import com.example.phundal2091.nbcnews.response.ItemsResponse
import io.reactivex.Observable
import io.reactivex.functions.Function
import java.util.ArrayList

/**
 * Created by phundal2091 on 5/9/18.
 */
class ApiService(val retrofitProvider: IRetrofitProvider, val restClient: IRestClient = retrofitProvider.getRetrofit().create(IRestClient::class.java)) : IApiService {

    override fun getCuratedContentAsync() : Observable<List<ItemsResponse>> {
        return restClient.getCuratedContent().map({ curatedContentResponses ->
            val itemsResponses = ArrayList<ItemsResponse>()
            if (curatedContentResponses != null && curatedContentResponses.size == 1) {
                val (_, _, _, _, _, items) = curatedContentResponses[0]
                if (items != null && items.isNotEmpty()) {
                    for (itemsResponse in items) {
                        if (itemsResponse._type != null && itemsResponse._type != Constants.article) {
                            itemsResponses.add(itemsResponse)
                        } else if (itemsResponse._type != null
                                && itemsResponse._type == Constants.article
                                && itemsResponse._breaking != null
                                && itemsResponse._breaking) {
                            itemsResponses.add(itemsResponse)
                        }
                    }
                }
            }
            itemsResponses
        })
    }


}