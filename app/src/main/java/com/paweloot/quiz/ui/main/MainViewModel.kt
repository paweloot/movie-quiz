package com.paweloot.quiz.ui.main

import androidx.lifecycle.ViewModel
import com.paweloot.quiz.entity.PhotoQuestion
import com.paweloot.quiz.entity.Question

class MainViewModel : ViewModel() {

    val photoQuestions: List<PhotoQuestion> = mutableListOf(
        PhotoQuestion(
            "https://storage.googleapis.com/afs-prod/media/media:e3be851cc3124c5f81f1489db36191fc/800.jpeg",
            "Henry Cavill"
        ),
        PhotoQuestion(
            "https://media-manager.starsinsider.com/1920/na_5dc05f1c9c55b.jpg",
            "Adam Driver"
        ),
        PhotoQuestion(
            "https://www.cheatsheet.com/wp-content/uploads/2019/11/daisy-ridley-in-a-black-dress-1024x763.jpg",
            "Daisy Ridley"
        )
    )

    val questions: List<Question> = mutableListOf()
}
