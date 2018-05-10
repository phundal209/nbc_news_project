package com.example.phundal2091.nbcnews.network

import com.example.phundal2091.nbcnews.response.CuratedContentResponse
import io.reactivex.Observable
import io.reactivex.functions.Function

/**
 * Created by phundal2091 on 5/9/18.
 */
class ApiService(val retrofitProvider: IRetrofitProvider, val restClient: IRestClient = retrofitProvider.getRetrofit().create(IRestClient::class.java)) : IApiService {
    
    override fun getCuratedContentAsync() : Observable<CuratedContentResponse> {
        return restClient.getCuratedContent()
    }


}