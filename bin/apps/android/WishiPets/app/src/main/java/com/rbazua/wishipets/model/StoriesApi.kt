package com.rbazua.wishipets.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface StoriesApi {
    @GET("storiesPaged")
    fun getStories(
        @Query("pageSize") storiesPaged : String ,
        @Query("lastDate") lastDate : String)
            : Single<List<Story>>
}