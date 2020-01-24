package com.paweloot.quiz.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.paweloot.quiz.R
import com.paweloot.quiz.utility.PlayerUtils
import kotlinx.android.synthetic.main.fragment_exo_player.*

private const val CLIP_ASSET_PATH = "clipAssetPath"

class ExoPlayerFragment : Fragment() {

    private lateinit var player: SimpleExoPlayer
    private var playbackPosition: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_exo_player, container, false)
    }

    override fun onResume() {
        super.onResume()
        initializePlayer()
        loadClip()
    }

    override fun onPause() {
        super.onPause()
        stopPlayback()
        releasePlayer()
    }

    private fun initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(context)
        player.seekTo(playbackPosition)
        playerView.player = player
    }

    private fun releasePlayer() {
        playbackPosition = player.currentPosition
        player.release()
    }

    private fun loadClip() {
        val mediaSource = PlayerUtils.buildMediaSource(
            requireContext(),
            arguments?.getString(CLIP_ASSET_PATH) ?: ""
        )

        player.prepare(mediaSource, false, false)
        player.playWhenReady = false
    }

    private fun stopPlayback() {
        player.playWhenReady = false
    }

    companion object {
        fun newInstance(clipAssetPath: String): ExoPlayerFragment =
            ExoPlayerFragment().apply {
                arguments = Bundle().apply {
                    putString(CLIP_ASSET_PATH, clipAssetPath)
                }
            }
    }
}