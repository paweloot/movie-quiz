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
import kotlinx.android.synthetic.main.fragment_sound.*

private const val SOUND_ASSET_PATH = "soundAssetPath"

class SoundFragment : Fragment() {

    private lateinit var player: SimpleExoPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_sound, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        playPauseButton.setOnClickListener {
            if (!player.isPlaying)
                startPlayback()
            else
                stopPlayback()
        }
    }

    override fun onResume() {
        super.onResume()
        initializePlayer()
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    private fun initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(context)
        player.playWhenReady = false
    }

    private fun releasePlayer() {
        stopPlayback()
        player.release()
    }

    private fun startPlayback() {
        val mediaSource = PlayerUtils.buildMediaSource(
            requireContext(),
            arguments?.getString(SOUND_ASSET_PATH) ?: ""
        )
        player.prepare(mediaSource)
        player.playWhenReady = true

        playPauseButton.setImageResource(R.drawable.exo_controls_pause)
    }

    private fun stopPlayback() {
        player.playWhenReady = false

        playPauseButton.setImageResource(R.drawable.exo_controls_play)
    }

    companion object {
        fun newInstance(soundAssetPath: String): SoundFragment =
            SoundFragment().apply {
                arguments = Bundle().apply {
                    putString(SOUND_ASSET_PATH, soundAssetPath)
                }
            }
    }
}