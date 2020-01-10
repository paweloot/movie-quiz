package com.paweloot.quiz.ui.learning

import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.AssetDataSource
import com.google.android.exoplayer2.upstream.DataSource
import com.paweloot.quiz.R
import com.paweloot.quiz.entity.SoundQuestion
import com.paweloot.quiz.ui.main.MainViewModel
import kotlinx.android.synthetic.main.music_fragment.*

class MusicFragment : Fragment() {

    companion object {
        fun newInstance() = MusicFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var player: SimpleExoPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.music_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        soundtrack_list.layoutManager = LinearLayoutManager(context)
        soundtrack_list.adapter = MusicAdapter(viewModel.soundQuestions, this::onSoundtrackClicked)

        initializePlayer()
    }

    private fun initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(requireContext())
    }

    private fun onSoundtrackClicked(soundQuestion: SoundQuestion) {

        when {
            player.isPlaying -> {
                player.stop()
            }
            else -> {
                val audioUri = Uri.parse("assets:///${soundQuestion.soundtrackAssetName}")
                val mediaSource = buildMediaSource(audioUri)

                player.prepare(mediaSource)
                player.playWhenReady = true
            }
        }

    }

    private fun buildMediaSource(uri: Uri): ProgressiveMediaSource? {
        val dataSourceFactory = DataSource.Factory { AssetDataSource(requireActivity()) }

        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(uri)
    }
}