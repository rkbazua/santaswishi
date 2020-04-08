package com.rbazua.wishipets.model

import com.rbazua.wishipets.util.PetsConstants
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class StoriesApiService {
    private val baseURL = PetsConstants.BASE_SERVICE_URL

    private val api = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(StoriesApi::class.java)

    fun getStories(): Single<List<Story>>{
        return api.getStories()
    }
}