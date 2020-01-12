package com.paweloot.quiz.entity

data class PhotoQuestion(
    val photoAnswer: PhotoAnswer,
    val wrongAnswers: List<String>
)