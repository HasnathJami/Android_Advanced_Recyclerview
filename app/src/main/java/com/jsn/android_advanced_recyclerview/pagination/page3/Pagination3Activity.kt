package com.jsn.android_advanced_recyclerview.pagination.page3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jsn.android_advanced_recyclerview.R
import com.jsn.android_advanced_recyclerview.network.ApiClient
import com.jsn.android_advanced_recyclerview.network.ApiService
import com.jsn.android_advanced_recyclerview.pagination.PhotoAdapter
import com.jsn.android_advanced_recyclerview.pagination.PhotoViewModel
import com.jsn.android_advanced_recyclerview.pagination.PhotoViewModelFactory
import com.jsn.android_advanced_recyclerview.repository.PhotoRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class Pagination3Activity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var photoAdapter: PhotoAdapterForPage3
    private val viewModel: PhotoViewModel by viewModels {
        PhotoViewModelFactory(PhotoRepository(ApiClient.getClient().create(ApiService::class.java)))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagination3)

        recyclerView = findViewById(R.id.rvPagination3Parent)
        recyclerView.layoutManager = LinearLayoutManager(this)
        photoAdapter = PhotoAdapterForPage3()
        recyclerView.adapter = photoAdapter

        val clientId = "OUg9nEwkcBahHWEaHQElH-iaXLzdSebX-sc8sWJunyQ" // Replace with your actual client ID

        lifecycleScope.launch {
            viewModel.getPhotos(clientId).collectLatest { pagingData ->
                photoAdapter.submitData(pagingData)
            }
        }
    }
}