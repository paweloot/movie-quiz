package com.paweloot.quiz.ui.main

import androidx.lifecycle.ViewModel
import com.paweloot.quiz.questions.ClipDataProvider
import com.paweloot.quiz.questions.PhotoDataProvider
import com.paweloot.quiz.questions.SoundtrackDataProvider

class MainViewModel : ViewModel() {

    val photoData = PhotoDataProvider.photoData

    val soundtrackData = SoundtrackDataProvider.soundtrackData

    val clipData = ClipDataProvider.clipData
}
