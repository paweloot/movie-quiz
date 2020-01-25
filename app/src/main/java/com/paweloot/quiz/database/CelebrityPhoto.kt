package com.paweloot.quiz.database

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@Entity(tableName = "celebrity_photo_table")
data class CelebrityPhoto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "celebrity_name") val celebrityName: String,
    @ColumnInfo(name = "bitmap_byte_array") val bitmapByteArray: ByteArray
)

@BindingAdapter("imageByteArray")
fun loadImageFromByteArray(view: ImageView, byteArray: ByteArray) {
    val circularProgressDrawable = CircularProgressDrawable(view.context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 60f
    circularProgressDrawable.start()

    Glide.with(view.context)
        .load(byteArray)
        .placeholder(circularProgressDrawable)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .skipMemoryCache(true)
        .into(view)
}