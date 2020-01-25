package com.paweloot.quiz.ui.learning

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paweloot.quiz.databinding.ListItemPhotoBinding
import com.paweloot.quiz.model.PhotoData

class PhotoAdapter(private val photoData: List<PhotoData>) :
    RecyclerView.Adapter<PhotoAdapter.PhotoHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding =
            ListItemPhotoBinding.inflate(inflater, parent, false)

        return PhotoHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.binding.photoQuestion = photoData[position]
    }

    override fun getItemCount() = photoData.size

    inner class PhotoHolder(val binding: ListItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}