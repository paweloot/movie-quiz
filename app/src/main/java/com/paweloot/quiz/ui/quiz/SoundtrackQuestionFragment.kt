package com.paweloot.quiz.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.paweloot.quiz.databinding.FragmentSoundtrackQuestionBinding
import com.paweloot.quiz.extension.allAnswers

class SoundtrackQuestionFragment : Fragment() {

    private lateinit var viewModel: QuizViewModel
    private lateinit var binding: FragmentSoundtrackQuestionBinding

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

        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)

        val answers = viewModel.soundtrackQuestion.allAnswers()

        binding.answerList.layoutManager = LinearLayoutManager(context)
        binding.answerList.adapter = AnswerAdapter(answers, this::onAnswerSelected)

        disableActionBarBackArrow()
    }

    private fun onAnswerSelected(answer: String) {
        viewModel.onSoundtrackAnswerSelected(answer)
        navigateToClipQuestion()
    }

    private fun navigateToClipQuestion() {
        findNavController()
            .navigate(SoundtrackQuestionFragmentDirections.actionSoundtrackQuestionFragmentToClipQuestionFragment())
    }

    private fun disableActionBarBackArrow() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            isEnabled = true
        }
    }
}