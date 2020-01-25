package com.paweloot.quiz.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.paweloot.quiz.MainActivity
import com.paweloot.quiz.R
import com.paweloot.quiz.model.Answer
import com.paweloot.quiz.model.AnswerKind
import kotlinx.android.synthetic.main.fragment_quiz.*

class QuizFragment : Fragment() {

    private lateinit var viewModel: QuizViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            (requireActivity() as MainActivity).showLeaveQuizDialog()
        }

        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)

        answerList.layoutManager = LinearLayoutManager(context)

        observeQuizState()
        setNextButtonOnClickListener()
    }

    private fun observeQuizState() {
        viewModel.quizState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                QuizState.QUESTION_PHOTO -> {
                    buildQuestionScreen(
                        R.string.guess_the_celebrity,
                        PictureFragment.newInstance(viewModel.photoQuestion.photo.photoUrl),
                        viewModel.photoQuestion.answers
                    )
                }
                QuizState.QUESTION_SOUNDTRACK -> {
                    buildQuestionScreen(
                        R.string.sounds_familiar,
                        SoundFragment.newInstance(viewModel.soundtrackQuestion.soundtrack.soundtrackAssetName),
                        viewModel.soundtrackQuestion.answers
                    )
                }
                QuizState.QUESTION_CLIP -> {
                    buildQuestionScreen(
                        R.string.looks_familiar,
                        ExoPlayerFragment.newInstance(viewModel.clipQuestion.clip.clipAssetName),
                        viewModel.clipQuestion.answers
                    )
                }
                QuizState.RESULT_PHOTO -> {
                    buildResultScreen(
                        R.string.guess_the_celebrity,
                        PictureFragment.newInstance(viewModel.photoQuestion.photo.photoUrl)
                    )
                }
                QuizState.RESULT_SOUNDTRACK -> {
                    buildResultScreen(
                        R.string.sounds_familiar,
                        SoundFragment.newInstance(viewModel.soundtrackQuestion.soundtrack.soundtrackAssetName)
                    )
                }
                QuizState.RESULT_CLIP -> {
                    buildResultScreen(
                        R.string.looks_familiar,
                        ExoPlayerFragment.newInstance(viewModel.clipQuestion.clip.clipAssetName)
                    )
                }
            }
        })
    }

    private fun onAnswerSelected(answer: String) {
        when (viewModel.quizState.value) {
            QuizState.QUESTION_PHOTO -> viewModel.selectedPhotoAnswer = answer
            QuizState.QUESTION_SOUNDTRACK -> viewModel.selectedSoundtrackAnswer = answer
            QuizState.QUESTION_CLIP -> viewModel.selectedClipAnswer = answer
        }
    }

    private fun setNextButtonOnClickListener() {
        nextButton.setOnClickListener {
            if (isAnyAnswerSelected()) {
                when (viewModel.quizState.value) {
                    QuizState.QUESTION_PHOTO -> changeStateTo(QuizState.QUESTION_SOUNDTRACK)
                    QuizState.QUESTION_SOUNDTRACK -> changeStateTo(QuizState.QUESTION_CLIP)
                    QuizState.QUESTION_CLIP -> changeStateTo(QuizState.RESULT_PHOTO)
                    QuizState.RESULT_PHOTO -> changeStateTo(QuizState.RESULT_SOUNDTRACK)
                    QuizState.RESULT_SOUNDTRACK -> changeStateTo(QuizState.RESULT_CLIP)
                    QuizState.RESULT_CLIP -> navigateToMenu()
                }
            } else {
                val toast = Toast.makeText(
                    context,
                    getString(R.string.no_answer_selected_toast),
                    Toast.LENGTH_SHORT
                )

                toast.show()
            }
        }
    }

    private fun isAnyAnswerSelected(): Boolean {
        return when (viewModel.quizState.value) {
            QuizState.QUESTION_PHOTO -> viewModel.selectedPhotoAnswer.isNotBlank()
            QuizState.QUESTION_SOUNDTRACK -> viewModel.selectedSoundtrackAnswer.isNotBlank()
            QuizState.QUESTION_CLIP -> viewModel.selectedClipAnswer.isNotBlank()
            else -> true
        }
    }

    private fun buildQuestionScreen(
        titleResource: Int,
        mediaFragment: Fragment,
        answers: List<String>
    ) {
        quizTitle.text = getString(titleResource)

        childFragmentManager.beginTransaction()
            .replace(R.id.mediaFragmentContainer, mediaFragment)
            .commit()

        answerList.adapter =
            AnswerAdapter(
                answers,
                this::onAnswerSelected
            )
    }

    private fun buildResultScreen(titleResource: Int, mediaFragment: Fragment) {
        quizTitle.text = getString(titleResource)

        childFragmentManager.beginTransaction()
            .replace(R.id.mediaFragmentContainer, mediaFragment)
            .commit()

        val resultAnswers = buildResultAnswers()
        answerList.adapter =
            ResultAnswerAdapter(
                resultAnswers
            )
    }

    private fun buildResultAnswers(): List<Answer> {
        val answers = when (viewModel.quizState.value) {
            QuizState.RESULT_PHOTO -> viewModel.photoQuestion.answers
            QuizState.RESULT_SOUNDTRACK -> viewModel.soundtrackQuestion.answers
            QuizState.RESULT_CLIP -> viewModel.clipQuestion.answers
            else -> throw IllegalStateException()
        }

        val correctAnswer = when (viewModel.quizState.value) {
            QuizState.RESULT_PHOTO -> viewModel.photoQuestion.photo.answer
            QuizState.RESULT_SOUNDTRACK -> viewModel.soundtrackQuestion.soundtrack.soundtrackTitle
            QuizState.RESULT_CLIP -> viewModel.clipQuestion.clip.movieTitle
            else -> throw IllegalStateException()
        }

        val selectedAnswer = when (viewModel.quizState.value) {
            QuizState.RESULT_PHOTO -> viewModel.selectedPhotoAnswer
            QuizState.RESULT_SOUNDTRACK -> viewModel.selectedSoundtrackAnswer
            QuizState.RESULT_CLIP -> viewModel.selectedClipAnswer
            else -> throw IllegalStateException()
        }

        return answers.map { answer ->
            Answer(
                answer,
                when (answer) {
                    correctAnswer -> AnswerKind.CORRECT
                    selectedAnswer -> AnswerKind.INCORRECT
                    else -> AnswerKind.NEUTRAL
                }
            )
        }
    }

    private fun changeStateTo(newState: Int) {
        viewModel.quizState.value = newState
    }

    private fun navigateToMenu() {
        findNavController()
            .navigate(QuizFragmentDirections.actionQuizFragmentToMainFragment())
    }
}