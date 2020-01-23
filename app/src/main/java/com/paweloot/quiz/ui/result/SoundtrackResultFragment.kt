package com.paweloot.quiz.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.paweloot.quiz.R
import com.paweloot.quiz.databinding.FragmentSoundtrackQuestionBinding
import com.paweloot.quiz.entity.Answer
import com.paweloot.quiz.entity.AnswerKind
import com.paweloot.quiz.extension.allAnswers
import com.paweloot.quiz.ui.quiz.QuizViewModel
import com.paweloot.quiz.utility.PlayerUtils
import kotlinx.android.synthetic.main.fragment_soundtrack_question.*

class SoundtrackResultFragment : Fragment() {

    private lateinit var viewModel: QuizViewModel
    private lateinit var binding: FragmentSoundtrackQuestionBinding

    private lateinit var player: SimpleExoPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            FragmentSoundtrackQuestionBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = activity?.let { ViewModelProvider(it).get(QuizViewModel::class.java) }!!

        val answers = viewModel.soundtrackQuestion.allAnswers()
        val correctAnswer = viewModel.soundtrackQuestion.soundtrack.soundtrackTitle
        val selectedAnswer = viewModel.data.value?.selectedSoundtrackAnswer

        val resultAnswers = answers.map { answer ->
            Answer(
                answer,
                when (answer) {
                    correctAnswer -> AnswerKind.CORRECT
                    selectedAnswer -> AnswerKind.INCORRECT
                    else -> AnswerKind.NEUTRAL
                }
            )
        }

        binding.answerList.layoutManager = LinearLayoutManager(context)
        binding.answerList.adapter = AnswerAdapter(resultAnswers)

        playPauseButton.setOnClickListener {
            when {
                player.isPlaying -> stopPlayback()
                else -> startPlayback()
            }
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
            viewModel.soundtrackQuestion.soundtrack.soundtrackAssetName
        )
        player.prepare(mediaSource)
        player.playWhenReady = true

        playPauseButton.setImageResource(R.drawable.exo_controls_pause)
    }

    private fun stopPlayback() {
        player.playWhenReady = false

        playPauseButton.setImageResource(R.drawable.exo_controls_play)
    }
}