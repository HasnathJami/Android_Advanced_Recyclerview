package com.jsn.android_advanced_recyclerview.pagination.page3

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jsn.android_advanced_recyclerview.model.Photo
import com.jsn.android_advanced_recyclerview.network.ApiService
import retrofit2.HttpException
import java.io.IOException

class PhotoPagingSource(
    private val api: ApiService,
    private val clientId: String
) : PagingSource<Int, Photo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val page = params.key ?: 1
        return try {
            val photos = api.getPhotos(page, params.loadSize, clientId)
            LoadResult.Page(
                data = photos,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (photos.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}
