package com.paweloot.quiz.ui.learning

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.paweloot.quiz.database.CelebrityPhotoRepository
import com.paweloot.quiz.database.QuizDatabase
import com.paweloot.quiz.model.CelebrityPhoto

class PhotoViewModel(application: Application) : AndroidViewModel(application) {

    private val photoDao = QuizDatabase.getInstance(application).photoDatabaseDao
    private val repository =
        CelebrityPhotoRepository(photoDao)

    val celebrityPhotos: LiveData<List<CelebrityPhoto>> = repository.findAll()

    fun fetchPhotos() {
        repository.fetchPhotos(getApplication())
    }
}