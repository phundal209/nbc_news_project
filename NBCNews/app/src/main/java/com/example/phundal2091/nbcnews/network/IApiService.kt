package com.example.phundal2091.nbcnews.network

import com.example.phundal2091.nbcnews.response.CuratedContentResponse
import io.reactivex.Observable

/**
 * Created by phundal2091 on 5/9/18.
 */
interface IApiService {

    fun getCuratedContentAsync() : Observable<CuratedContentResponse>
}