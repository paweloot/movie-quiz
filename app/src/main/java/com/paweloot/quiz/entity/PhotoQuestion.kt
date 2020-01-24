package com.paweloot.quiz.entity

data class PhotoQuestion(
    val photo: PhotoData,
    val answers: List<String>
)