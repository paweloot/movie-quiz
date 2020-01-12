package com.paweloot.quiz.ui.learning

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paweloot.quiz.databinding.ListItemClipBinding
import com.paweloot.quiz.entity.ClipQuestion

class ClipAdapter(
    private val clipQuestions: List<ClipQuestion>,
    private val callback: (clipQuestion: ClipQuestion) -> Unit
) :
    RecyclerView.Adapter<ClipAdapter.ClipHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClipHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding =
            ListItemClipBinding.inflate(inflater, parent, false)

        return ClipHolder(binding)
    }

    override fun onBindViewHolder(holder: ClipHolder, position: Int) {
        holder.binding.clipQuestion = clipQuestions[position]
    }

    override fun getItemCount() = clipQuestions.size

    inner class ClipHolder(val binding: ListItemClipBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                callback(binding.clipQuestion!!)
            }
        }
    }
}