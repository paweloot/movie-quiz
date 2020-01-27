package com.paweloot.quiz.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "celebrity_photo_table")
data class CelebrityPhoto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "celebrity_name") val celebrityName: String,
    @ColumnInfo(name = "bitmap_byte_array") val bitmapByteArray: ByteArray
)