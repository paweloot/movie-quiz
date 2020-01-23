package com.paweloot.quiz.ui.learning

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.AssetDataSource
import com.google.android.exoplayer2.upstream.DataSource
import com.paweloot.quiz.databinding.FragmentClipBinding
import com.paweloot.quiz.entity.ClipQuestion
import com.paweloot.quiz.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_clip.*

private const val GRID_COL_COUNT = 2

class ClipFragment : Fragment() {

    companion object {
        fun newInstance() = ClipFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentClipBinding
    private lateinit var player: SimpleExoPlayer

    private lateinit var currentClip: ClipQuestion
    private var playWhenReady: Boolean = true
    private var playbackPosition: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentClipBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        clip_list.layoutManager = GridLayoutManager(context, GRID_COL_COUNT)
        clip_list.adapter = ClipAdapter(viewModel.clipData, this::onClipSelected)

        currentClip = viewModel.clipData[0]
        binding.clipQuestion = currentClip

        next_button.setOnClickListener {

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
        player_view.player = player

        player.playWhenReady = playWhenReady
        player.seekTo(playbackPosition)

        if (playbackPosition > 0) {
            val mediaSource = buildMediaSource(currentClip)
            player.prepare(mediaSource, false, false)
        }
    }

    private fun releasePlayer() {
        playWhenReady = player.playWhenReady
        playbackPosition = player.currentPosition
        player.release()
    }

    private fun onClipSelected(clipQuestion: ClipQuestion) {

        scrollToTop()

        when {
            currentClip == clipQuestion -> {
                if (player.isPlaying)
                    stopPlayback()

            }
            currentClip != clipQuestion -> {
                stopPlayback()
                currentClip = clipQuestion
                binding.clipQuestion = currentClip
            }
        }

        val mediaSource = buildMediaSource(clipQuestion)
        startPlayback(mediaSource)
    }

    private fun startPlayback(mediaSource: MediaSource?) {
        player.prepare(mediaSource)
        player.playWhenReady = true
    }


    private fun stopPlayback() {
        player.playWhenReady = false
    }

    private fun buildMediaSource(clipQuestion: ClipQuestion): ProgressiveMediaSource? {
        val clipUri = Uri.parse("assets:///clips/${clipQuestion.clipAssetName}")
        val dataSourceFactory = DataSource.Factory { AssetDataSource(requireActivity()) }

        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(clipUri)
    }

    private fun scrollToTop() {
        scrollView.post(Runnable {
            scrollView.fullScroll(scrollView.top)
        })
    }
}