package com.paweloot.quiz.entity

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

data class PhotoData(
    val photoUrl: String,
    val answer: String
)

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    val circularProgressDrawable = CircularProgressDrawable(view.context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 60f
    circularProgressDrawable.start()

    Glide.with(view.context)
        .load(url)
        .placeholder(circularProgressDrawable)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .skipMemoryCache(true)
        .into(view)
}