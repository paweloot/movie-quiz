package com.paweloot.quiz.ui.learning

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.paweloot.quiz.databinding.ListItemSoundtrackBinding
import com.paweloot.quiz.entity.SoundtrackData

class SoundtrackAdapter(
    private val soundtrackData: List<SoundtrackData>,
    private val callback: (button: ImageButton, soundtrackData: SoundtrackData) -> Unit
) :
    RecyclerView.Adapter<SoundtrackAdapter.MusicHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding =
            ListItemSoundtrackBinding.inflate(inflater, parent, false)

        return MusicHolder(binding)
    }

    override fun onBindViewHolder(holder: MusicHolder, position: Int) {
        holder.binding.soundtrackQuestion = soundtrackData[position]
    }

    override fun getItemCount() = soundtrackData.size

    inner class MusicHolder(val binding: ListItemSoundtrackBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.playPauseButton.setOnClickListener {
                callback(binding.playPauseButton, binding.soundtrackQuestion!!)
            }
        }
    }
}