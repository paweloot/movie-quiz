package com.paweloot.quiz.ui.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paweloot.quiz.data.ClipDataProvider
import com.paweloot.quiz.data.PhotoDataProvider
import com.paweloot.quiz.data.SoundtrackDataProvider
import com.paweloot.quiz.model.ClipQuestion
import com.paweloot.quiz.model.PhotoQuestion
import com.paweloot.quiz.model.SoundtrackQuestion

object QuizState {
    const val QUESTION_PHOTO = 0
    const val QUESTION_SOUNDTRACK = 1
    const val QUESTION_CLIP = 2
    const val RESULT_PHOTO = 3
    const val RESULT_SOUNDTRACK = 4
    const val RESULT_CLIP = 5
}

class QuizViewModel : ViewModel() {

    val quizState = MutableLiveData<Int>(QuizState.QUESTION_PHOTO)

    val photoQuestion: PhotoQuestion = PhotoDataProvider.getRandomQuestion()
    val soundtrackQuestion: SoundtrackQuestion = SoundtrackDataProvider.getRandomQuestion()
    val clipQuestion: ClipQuestion = ClipDataProvider.getRandomQuestion()
    var selectedPhotoAnswer: String = ""
    var selectedSoundtrackAnswer: String = ""
    var selectedClipAnswer: String = ""
}