package com.jsn.android_advanced_recyclerview.repository

import com.jsn.android_advanced_recyclerview.model.Photo
import com.jsn.android_advanced_recyclerview.network.ApiService
import retrofit2.HttpException
import java.io.IOException

class PhotoRepository(private val api: ApiService) {
    suspend fun fetchPhotos(page: Int, perPage: Int, clientId: String): List<Photo> {
        return try {
            api.getPhotos(page, perPage, clientId)
        } catch (e: IOException) {
            // Handle network error
            emptyList()
        } catch (e: HttpException) {
            // Handle API error
            emptyList()
        }
    }
}