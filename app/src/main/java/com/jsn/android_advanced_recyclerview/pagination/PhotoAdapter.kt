package com.jsn.android_advanced_recyclerview.pagination

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jsn.android_advanced_recyclerview.R
import com.jsn.android_advanced_recyclerview.model.Photo

class PhotoAdapter(private var photos: List<Photo>) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

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
        val photo = photos[position]
        Glide.with(holder.itemView)
            .load(photo.urls.regular)
            .into(holder.imageView)
        holder.descriptionTextView.text = photo.description ?: "No Description"
        holder.userNameTextView.text = "By: ${photo.user.name}"
    }

    override fun getItemCount() = photos.size

    fun updatePhotos(newPhotos: List<Photo>) {
        photos = newPhotos
        notifyDataSetChanged()
    }
}
