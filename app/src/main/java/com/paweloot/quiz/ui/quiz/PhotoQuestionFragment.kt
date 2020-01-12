package com.paweloot.quiz.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.paweloot.quiz.databinding.FragmentPhotoQuestionBinding

class PhotoQuestionFragment : Fragment() {

    companion object {
        fun newInstance() = PhotoQuestionFragment()
    }

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

        viewModel = ViewModelProviders.of(this).get(QuizViewModel::class.java)

        binding.photoUrl = viewModel.randomPhotoQuestion.photoAnswer.photoUrl

        val answers = viewModel.randomPhotoQuestion.wrongAnswers.toMutableList()
        answers.add(viewModel.randomPhotoQuestion.photoAnswer.answer)
        answers.shuffle()

        binding.answerList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = AnswerAdapter(answers, this@PhotoQuestionFragment::onAnswerSelected)
        }
    }

    private fun onAnswerSelected(answer: String) {
        viewModel.selectedPhotoAnswer = answer
        navigateToSoundtrackQuestion()
    }

    private fun navigateToSoundtrackQuestion() {
        findNavController()
            .navigate(PhotoQuestionFragmentDirections.actionPhotoQuestionFragmentToSoundtrackQuestionFragment())
    }
}