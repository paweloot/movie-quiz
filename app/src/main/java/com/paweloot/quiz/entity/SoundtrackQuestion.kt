package com.paweloot.quiz.entity

data class SoundtrackQuestion(
    val soundtrack: SoundtrackData,
    val wrongAnswers: List<String>
)