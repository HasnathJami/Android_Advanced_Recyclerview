package com.jsn.android_advanced_recyclerview.network

import com.jsn.android_advanced_recyclerview.model.Photo
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("photos")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("client_id") clientId: String
    ): List<Photo>
}