package com.paweloot.quiz.entity

data class Question(
    val content: String,
    val answers: List<String>,
    val correctAnswer: Int
)