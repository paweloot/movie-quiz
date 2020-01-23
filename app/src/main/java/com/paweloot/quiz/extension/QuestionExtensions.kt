package com.paweloot.quiz.extension

import com.paweloot.quiz.entity.ClipQuestion
import com.paweloot.quiz.entity.PhotoQuestion
import com.paweloot.quiz.entity.SoundtrackQuestion

fun PhotoQuestion.allAnswers(): List<String> {
    return wrongAnswers.toMutableList().also {
        it.add(photo.answer)
        it.shuffle()
    }
}

fun SoundtrackQuestion.allAnswers(): List<String> {
    return wrongAnswers.toMutableList().also {
        it.add(soundtrack.soundtrackTitle)
        it.shuffle()
    }
}

fun ClipQuestion.allAnswers(): List<String> {
    return wrongAnswers.toMutableList().also {
        it.add(clip.movieTitle)
        it.shuffle()
    }
}