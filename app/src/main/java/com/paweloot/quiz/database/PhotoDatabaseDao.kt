package com.paweloot.quiz.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.paweloot.quiz.model.CelebrityPhoto

@Dao
interface PhotoDatabaseDao {

    @Query("SELECT * from celebrity_photo_table")
    fun findAll(): LiveData<List<CelebrityPhoto>>

    @Insert
    fun insertAll(celebrityPhotoList: List<CelebrityPhoto>)
}