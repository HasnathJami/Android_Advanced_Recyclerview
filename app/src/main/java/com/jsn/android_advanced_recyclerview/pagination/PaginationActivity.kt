package com.jsn.android_advanced_recyclerview.pagination

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jsn.android_advanced_recyclerview.R
import com.jsn.android_advanced_recyclerview.network.ApiClient
import com.jsn.android_advanced_recyclerview.network.ApiService
import com.jsn.android_advanced_recyclerview.repository.PhotoRepository

class PaginationActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    private lateinit var photoAdapter: PhotoAdapter
    private val viewModel: PhotoViewModel by viewModels {
        PhotoViewModelFactory(PhotoRepository(ApiClient.getClient().create(ApiService::class.java)))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagination)

        recyclerView = findViewById(R.id.rvPaginationParent)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        photoAdapter = PhotoAdapter(emptyList())
        recyclerView.adapter = photoAdapter

        val clientId = "OUg9nEwkcBahHWEaHQElH-iaXLzdSebX-sc8sWJunyQ" // Replace with your actual client ID
        viewModel.fetchPhotos(clientId)

        viewModel.photos.observe(this) { photos ->
            photoAdapter.updatePhotos(photos) // Update the adapter with new photos
        }

        // Pagination listener
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager

                // initially totaItemCount will be 5 cause we have loaded 5 data with api call
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
                Log.d("checkCountLayoutTotItem", totalItemCount.toString())
                Log.d("checkCountLastVisItem", lastVisibleItem.toString())
                Log.d("checkCountFirstVisItem", firstVisibleItem.toString())
                Log.d("checkCountTotVisItem", (lastVisibleItem-firstVisibleItem+1).toString())
                // initially totaItemCount will be 5 cause we have loaded 5 data with api call
                if (totalItemCount <= (lastVisibleItem + 2)) {
                    // Load more photos if end of the item has reached
                    viewModel.fetchPhotos(clientId)
                }
            }
        })
    }

    }