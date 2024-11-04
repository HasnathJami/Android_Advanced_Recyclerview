package com.jsn.android_advanced_recyclerview.pagination

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsn.android_advanced_recyclerview.model.Photo
import com.jsn.android_advanced_recyclerview.repository.PhotoRepository
import kotlinx.coroutines.launch

class PhotoViewModel(private val repository: PhotoRepository) : ViewModel() {
    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>> get() = _photos

    private var currentPage = 1
    private var isLoading = false

    fun fetchPhotos(clientId: String) {
        if (isLoading) return

        isLoading = true
        viewModelScope.launch {
            try {
                val photoList = repository.fetchPhotos(currentPage, 5, clientId)
                val updatedList = _photos.value.orEmpty() + photoList
                _photos.value = updatedList
                currentPage++
            } catch (e: Exception) {
                // Handle exceptions
                _photos.value = emptyList()
            } finally {
                isLoading = false
            }
        }
    }
}
