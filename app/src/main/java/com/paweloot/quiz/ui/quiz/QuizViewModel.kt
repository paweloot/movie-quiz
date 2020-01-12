package com.paweloot.quiz.ui.quiz

import androidx.lifecycle.ViewModel
import com.paweloot.quiz.entity.PhotoQuestion
import com.paweloot.quiz.questions.PhotoQuestionProvider

class QuizViewModel : ViewModel() {

    val randomPhotoQuestion: PhotoQuestion by lazy { PhotoQuestionProvider.getRandomQuestion() }
    var selectedPhotoAnswer: String? = null
    var selectedSoundtrackAnswer: String? = null
}