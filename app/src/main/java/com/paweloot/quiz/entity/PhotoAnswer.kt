package com.paweloot.quiz.entity

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide

data class PhotoAnswer(
    val photoUrl: String,
    val answer: String
)

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    val circularProgressDrawable = CircularProgressDrawable(view.context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    Glide.with(view.context)
        .load(url)
        .placeholder(circularProgressDrawable)
        .into(view)
}