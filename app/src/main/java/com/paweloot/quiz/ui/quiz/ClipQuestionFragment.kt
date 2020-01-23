package com.paweloot.quiz.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.paweloot.quiz.databinding.FragmentClipQuestionBinding
import com.paweloot.quiz.extension.allAnswers

class ClipQuestionFragment : Fragment() {

    private lateinit var viewModel: QuizViewModel
    private lateinit var binding: FragmentClipQuestionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentClipQuestionBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)

        val answers = viewModel.clipQuestion.allAnswers()

        binding.answerList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = AnswerAdapter(answers, this@ClipQuestionFragment::onAnswerSelected)
        }
    }

    private fun onAnswerSelected(answer: String) {
        viewModel.onCLipAnswerSelected(answer)
    }
}