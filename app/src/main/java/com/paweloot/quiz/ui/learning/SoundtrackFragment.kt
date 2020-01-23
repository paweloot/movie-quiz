package com.paweloot.quiz.ui.learning

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.AssetDataSource
import com.google.android.exoplayer2.upstream.DataSource
import com.paweloot.quiz.R
import com.paweloot.quiz.entity.SoundtrackData
import com.paweloot.quiz.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_soundtrack.*

class SoundtrackFragment : Fragment() {

    companion object {
        fun newInstance() = SoundtrackFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var player: SimpleExoPlayer

    private var playWhenReady: Boolean = true
    private var playbackPosition: Long = 0
    private var currentButton: ImageButton? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_soundtrack, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        soundtrack_list.layoutManager = LinearLayoutManager(context)
        soundtrack_list.adapter =
            SoundtrackAdapter(viewModel.soundtrackData, this::onSoundtrackClicked)

        next_button.setOnClickListener {
            findNavController()
                .navigate(SoundtrackFragmentDirections.actionMusicFragmentToClipFragment().actionId)
        }

        initializePlayer()
    }

    override fun onResume() {
        super.onResume()

        initializePlayer()
    }

    override fun onPause() {
        super.onPause()

        stopPlayback()
        releasePlayer()
    }

    private fun initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(requireContext())
        player.playWhenReady = playWhenReady
        player.seekTo(playbackPosition)
    }

    private fun releasePlayer() {
        playWhenReady = player.playWhenReady
        playbackPosition = player.currentPosition
        player.release()
    }

    private fun onSoundtrackClicked(button: ImageButton, soundtrackData: SoundtrackData) {

        when {
            currentButton != null && button == currentButton -> {
                if (player.isPlaying)
                    stopPlayback()
                else {
                    val mediaSource = buildMediaSource(soundtrackData)
                    startPlayback(mediaSource, false)
                }
            }
            currentButton != null && button != currentButton -> {
                stopPlayback()
                currentButton = button

                val mediaSource = buildMediaSource(soundtrackData)
                startPlayback(mediaSource, true)
            }
            currentButton == null -> {
                currentButton = button

                val mediaSource = buildMediaSource(soundtrackData)
                startPlayback(mediaSource, true)
            }
        }

    }

    private fun startPlayback(mediaSource: MediaSource?, reset: Boolean) {
        player.prepare(mediaSource, reset, reset)
        player.playWhenReady = true

        currentButton?.setImageResource(R.drawable.exo_controls_pause)
    }


    private fun stopPlayback() {
        player.playWhenReady = false
        currentButton?.setImageResource(R.drawable.exo_controls_play)
    }

    private fun buildMediaSource(soundtrackData: SoundtrackData): ProgressiveMediaSource? {
        val audioUri = Uri.parse("assets:///${soundtrackData.soundtrackAssetName}")
        val dataSourceFactory = DataSource.Factory { AssetDataSource(requireActivity()) }

        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(audioUri)
    }
}