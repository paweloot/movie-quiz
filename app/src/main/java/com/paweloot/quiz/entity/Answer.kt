package com.paweloot.quiz.entity

data class Answer(
    val content: String,
    val kind: Int
)

object AnswerKind {
    const val NEUTRAL = 0
    const val CORRECT = 1
    const val INCORRECT = 2
}