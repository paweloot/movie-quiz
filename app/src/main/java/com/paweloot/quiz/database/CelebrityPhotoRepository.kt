package com.paweloot.quiz.database

import android.app.Application
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.paweloot.quiz.data.PhotoDataProvider
import com.paweloot.quiz.model.CelebrityPhoto
import java.io.ByteArrayOutputStream
import java.util.concurrent.Executors

class CelebrityPhotoRepository(private val photoDatabaseDao: PhotoDatabaseDao) {

    fun findAll(): LiveData<List<CelebrityPhoto>> {
        return photoDatabaseDao.findAll()
    }

    fun fetchPhotos(application: Application) {
        val photoDataSize = PhotoDataProvider.photoData.size
        val celebrityPhotos = mutableListOf<CelebrityPhoto>()

        PhotoDataProvider.photoData.forEach { photoData ->
            Glide.with(application)
                .asBitmap()
                .load(photoData.photoUrl)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onLoadCleared(placeholder: Drawable?) {
                        // left blank on purpose
                    }

                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        val bos = ByteArrayOutputStream()
                        resource.compress(Bitmap.CompressFormat.JPEG, 100, bos)
                        val byteArray = bos.toByteArray()

                        celebrityPhotos.add(
                            CelebrityPhoto(
                                celebrityName = photoData.answer,
                                bitmapByteArray = byteArray
                            )
                        )

                        // on all photos fetched
                        if (celebrityPhotos.size == photoDataSize) {
                            val executor = Executors.newSingleThreadExecutor()
                            executor.execute {
                                photoDatabaseDao.insertAll(celebrityPhotos)
                            }
                        }
                    }
                })
        }
    }
}