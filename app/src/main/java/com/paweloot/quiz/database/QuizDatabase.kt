package com.paweloot.quiz.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.paweloot.quiz.model.CelebrityPhoto

@Database(entities = [CelebrityPhoto::class], version = 1, exportSchema = false)
abstract class QuizDatabase : RoomDatabase() {

    abstract val photoDatabaseDao: PhotoDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: QuizDatabase? = null

        fun getInstance(context: Context): QuizDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        QuizDatabase::class.java,
                        "quiz_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}