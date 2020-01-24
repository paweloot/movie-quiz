package com.paweloot.quiz.ui.main

import androidx.lifecycle.ViewModel
import com.paweloot.quiz.data.ClipDataProvider
import com.paweloot.quiz.data.PhotoDataProvider
import com.paweloot.quiz.data.SoundtrackDataProvider

class MainViewModel : ViewModel() {

    val photoData = PhotoDataProvider.photoData

    val soundtrackData = SoundtrackDataProvider.soundtrackData

    val clipData = ClipDataProvider.clipData
}
