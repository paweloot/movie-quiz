package com.paweloot.quiz.entity

data class ClipQuestion(
    val clip: ClipData,
    val wrongAnswers: List<String>
)