package com.paweloot.quiz.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.paweloot.quiz.databinding.FragmentClipQuestionBinding
import com.paweloot.quiz.extension.allAnswers
import com.paweloot.quiz.utility.PlayerUtils
import kotlinx.android.synthetic.main.fragment_clip_question.*

class ClipQuestionFragment : Fragment() {

    private lateinit var viewModel: QuizViewModel
    private lateinit var binding: FragmentClipQuestionBinding

    private lateinit var player: SimpleExoPlayer
    private var playbackPosition: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentClipQuestionBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.let { ViewModelProvider(it).get(QuizViewModel::class.java) }!!

        val answers = viewModel.clipQuestion.allAnswers()

        binding.answerList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = AnswerAdapter(answers, this@ClipQuestionFragment::onAnswerSelected)
        }
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

    private fun onAnswerSelected(answer: String) {
        viewModel.onCLipAnswerSelected(answer)
        navigateToPhotoResultFragment()
    }

    private fun navigateToPhotoResultFragment() {
        findNavController()
            .navigate(ClipQuestionFragmentDirections.actionClipQuestionFragmentToPhotoResultFragment())
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
            viewModel.clipQuestion.clip.clipAssetName
        )

        player.prepare(mediaSource, false, false)
        player.playWhenReady = false
    }

    private fun stopPlayback() {
        player.playWhenReady = false
    }
}