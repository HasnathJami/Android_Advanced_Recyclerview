package com.jsn.android_advanced_recyclerview.pagination.page3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jsn.android_advanced_recyclerview.R
import com.jsn.android_advanced_recyclerview.model.Photo

class PhotoAdapterForPage3 : PagingDataAdapter<Photo, PhotoAdapterForPage3.PhotoViewHolder>(PHOTO_COMPARATOR) {

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
        val descriptionTextView: TextView = itemView.findViewById(R.id.description_text_view)
        val userNameTextView: TextView = itemView.findViewById(R.id.user_name_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = getItem(position)
        if (photo != null) {
            Glide.with(holder.itemView)
                .load(photo.urls.regular)
                .into(holder.imageView)
            holder.descriptionTextView.text = photo.description ?: "No Description"
            holder.userNameTextView.text = "By: ${photo.user.name}"
        }
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem == newItem
            }
        }
    }
}
