package com.example.phundal2091.nbcnews.network

import com.example.phundal2091.nbcnews.response.CuratedContentResponse
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by phundal2091 on 5/9/18.
 */
interface IRestClient {
    @GET("/v1/query/curation/news/?size=100")
    fun getCuratedContent(): Observable<CuratedContentResponse>
}