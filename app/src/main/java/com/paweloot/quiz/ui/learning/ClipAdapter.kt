package com.paweloot.quiz.ui.learning

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paweloot.quiz.databinding.ListItemClipBinding
import com.paweloot.quiz.entity.ClipData

class ClipAdapter(
    private val clipData: List<ClipData>,
    private val callback: (clipData: ClipData) -> Unit
) :
    RecyclerView.Adapter<ClipAdapter.ClipHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClipHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding =
            ListItemClipBinding.inflate(inflater, parent, false)

        return ClipHolder(binding)
    }

    override fun onBindViewHolder(holder: ClipHolder, position: Int) {
        holder.binding.clipQuestion = clipData[position]
    }

    override fun getItemCount() = clipData.size

    inner class ClipHolder(val binding: ListItemClipBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                callback(binding.clipQuestion!!)
            }
        }
    }
}