package com.paweloot.quiz.model

data class Answer(
    val content: String,
    val kind: Int
)

object AnswerKind {
    const val NEUTRAL = 0
    const val CORRECT = 1
    const val INCORRECT = 2
}