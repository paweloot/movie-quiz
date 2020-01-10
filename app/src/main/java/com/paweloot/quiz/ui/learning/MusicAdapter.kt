package com.paweloot.quiz.ui.learning

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paweloot.quiz.databinding.ListItemSoundtrackBinding
import com.paweloot.quiz.entity.SoundQuestion

class MusicAdapter(
    private val soundQuestions: List<SoundQuestion>,
    private val callback: (soundQuestion: SoundQuestion) -> Unit
) :
    RecyclerView.Adapter<MusicAdapter.MusicHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding =
            ListItemSoundtrackBinding.inflate(inflater, parent, false)

        return MusicHolder(binding)
    }

    override fun onBindViewHolder(holder: MusicHolder, position: Int) {
        holder.binding.soundQuestion = soundQuestions[position]
    }

    override fun getItemCount() = soundQuestions.size

    inner class MusicHolder(val binding: ListItemSoundtrackBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                callback(binding.soundQuestion!!)
            }
        }
    }
}