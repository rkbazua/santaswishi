package com.rbazua.wishipets.model

import com.rbazua.wishipets.util.PetsConstants
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class StoriesApiService {
    private val baseURL = PetsConstants.BASE_SERVICE_URL

    private val api = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(StoriesApi::class.java)

    fun getStories(): Single<List<Story>>{
        val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val currentDate = sdf.format(Date())
        return api.getStories("5", currentDate.toString())
    }

    fun getStories(date: Date?, storiesPaged: String): Single<List<Story>>{
        val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        var formattedDate = date?.let { sdf.format(date)}
        if(formattedDate==null){
            var formattedDate = sdf.format(Date())
            return api.getStories(storiesPaged, formattedDate)
        }
        return api.getStories(storiesPaged, formattedDate)
    }
}