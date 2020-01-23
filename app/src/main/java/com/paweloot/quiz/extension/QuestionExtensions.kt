package com.paweloot.quiz.extension

import com.paweloot.quiz.entity.PhotoQuestion
import com.paweloot.quiz.entity.SoundtrackQuestion

fun PhotoQuestion.allAnswers(): List<String> {
    return wrongAnswers.toMutableList().also {
        it.add(photoAnswer.answer)
        it.shuffle()
    }
}

fun SoundtrackQuestion.allAnswers(): List<String> {
    return wrongAnswers.toMutableList().also {
        it.add(soundtrack.soundtrackTitle)
        it.shuffle()
    }
}