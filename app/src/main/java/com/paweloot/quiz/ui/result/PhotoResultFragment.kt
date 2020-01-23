package com.paweloot.quiz.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.paweloot.quiz.databinding.FragmentPhotoQuestionBinding
import com.paweloot.quiz.entity.Answer
import com.paweloot.quiz.entity.AnswerKind
import com.paweloot.quiz.extension.allAnswers
import com.paweloot.quiz.ui.quiz.QuizViewModel

class PhotoResultFragment : Fragment() {

    private lateinit var viewModel: QuizViewModel
    private lateinit var binding: FragmentPhotoQuestionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            FragmentPhotoQuestionBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.let { ViewModelProvider(it).get(QuizViewModel::class.java) }!!

        binding.photoUrl = viewModel.photoQuestion.photo.photoUrl

        val answers = viewModel.photoQuestion.allAnswers()
        val correctAnswer = viewModel.photoQuestion.photo.answer
        val selectedAnswer = viewModel.data.value?.selectedPhotoAnswer

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

        binding.answerList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = AnswerAdapter(resultAnswers)
        }
    }

    private fun navigateToSoundtrackQuestion() {

    }
}