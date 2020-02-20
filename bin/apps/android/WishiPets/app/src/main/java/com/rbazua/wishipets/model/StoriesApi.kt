package com.rbazua.wishipets.model

import io.reactivex.Single
import retrofit2.http.GET

interface StoriesApi {
    @GET("storiesPaged")
    fun getStories() : Single<List<Story>>
}