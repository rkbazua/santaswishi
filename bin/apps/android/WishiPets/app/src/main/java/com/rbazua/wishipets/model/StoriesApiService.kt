package com.rbazua.wishipets.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class StoriesApiService {
    private val BASE_URL = "https://ftc8bwat3c.execute-api.us-east-1.amazonaws.com/dev/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(StoriesApi::class.java)

    fun getStories(): Single<List<Story>>{
        return api.getStories()
    }
}