package com.jsn.android_advanced_recyclerview.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jsn.android_advanced_recyclerview.model.Photo
import com.jsn.android_advanced_recyclerview.network.ApiService
import com.jsn.android_advanced_recyclerview.pagination.page3.PhotoPagingSource
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException

class PhotoRepository(private val api: ApiService) {
    val pagingSource = PhotoPagingSource(api, "OUg9nEwkcBahHWEaHQElH-iaXLzdSebX-sc8sWJunyQ")
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

    // Maximum size must be at least pageSize + 2*prefetchDist
    // maxSize = 30. page 3 will retails most recent 30 items that have been loaded. If we scroll back to top after this 30 items, the api call will be occurred again
    // prefetchDistance = 3 . items 3 will be fetched previously. when it already fetches 5 items. so 5+3 = 8
    fun getPhotosPagingData(clientId: String): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(pageSize = 5, enablePlaceholders = false, prefetchDistance = 3, maxSize = 30),
            pagingSourceFactory = { pagingSource }
        ).flow
    }

}