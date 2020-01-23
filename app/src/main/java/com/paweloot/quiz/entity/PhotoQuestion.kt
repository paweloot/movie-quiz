package com.paweloot.quiz.entity

data class PhotoQuestion(
    val photo: PhotoData,
    val wrongAnswers: List<String>
)