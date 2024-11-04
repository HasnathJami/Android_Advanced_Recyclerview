package com.jsn.android_advanced_recyclerview.pagination

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jsn.android_advanced_recyclerview.repository.PhotoRepository

class PhotoViewModelFactory(private val repository: PhotoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotoViewModel::class.java)) {
            return PhotoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
