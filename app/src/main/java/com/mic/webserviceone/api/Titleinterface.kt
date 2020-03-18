package com.mic.webserviceone.api

import com.mic.webserviceone.model.title
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Titleinterface {
    @GET("posts")
    fun getTitle():Call<List<title>>

    @GET("posts/{id}")
    fun getSearchList(
        @Path("id") userId:String
    ):Call<title>

    @GET("top-headlines")
    fun getNews(
        @Query("country") country:String,
        @Query("category") category: String,
        @Query("apiKey") apiKey:String
    ): Call<News>
}