package com.example.phundal2091.nbcnews.network

import retrofit2.Retrofit

/**
 * Created by phundal2091 on 5/9/18.
 */
interface IRetrofitProvider {
    fun getRetrofit() : Retrofit
}