package com.paweloot.quiz.ui.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paweloot.quiz.entity.PhotoQuestion
import com.paweloot.quiz.entity.SoundtrackQuestion
import com.paweloot.quiz.questions.PhotoDataProvider
import com.paweloot.quiz.questions.SoundtrackDataProvider

class QuizViewModel : ViewModel() {

    data class QuizData(
        val photoQuestion: PhotoQuestion = PhotoDataProvider.getRandomQuestion(),
        val selectedPhotoAnswer: String = "",
        val soundtrackQuestion: SoundtrackQuestion = SoundtrackDataProvider.getRandomQuestion(),
        val selectedSoundtrackAnswer: String = ""
    )

    val data =
        MutableLiveData<QuizData>().also { it.value = QuizData() }

    val photoQuestion = data.value?.photoQuestion!!
    val soundtrackQuestion = data.value?.soundtrackQuestion!!

    fun onPhotoAnswerSelected(answer: String) {
        data.value = data.value?.copy(
            selectedPhotoAnswer = answer
        )
    }

    fun onSoundtrackAnswerSelected(answer: String) {
        data.value = data.value?.copy(
            selectedSoundtrackAnswer = answer
        )
    }
}