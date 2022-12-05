package com.turgutkurt.boynermobilandroid.services

import com.turgutkurt.boynermobilandroid.model.NewsResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("sources")
    fun getSources(@Query("language") language: String?, @Query("apiKey") apiKey: String?):  Call<NewsResponseModel>
}