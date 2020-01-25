package com.paweloot.quiz.ui.learning

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paweloot.quiz.databinding.ListItemPhotoBinding
import com.paweloot.quiz.model.CelebrityPhoto

class PhotoAdapter :
    RecyclerView.Adapter<PhotoAdapter.PhotoHolder>() {

    var photoData: List<CelebrityPhoto> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding =
            ListItemPhotoBinding.inflate(inflater, parent, false)

        return PhotoHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.binding.celebrityPhoto = photoData[position]
    }

    override fun getItemCount() = photoData.size

    inner class PhotoHolder(val binding: ListItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}