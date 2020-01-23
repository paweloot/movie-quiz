package com.paweloot.quiz.utility

import android.content.Context
import android.net.Uri
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.AssetDataSource
import com.google.android.exoplayer2.upstream.DataSource

object PlayerUtils {

    fun buildMediaSource(context: Context, assetPath: String): ProgressiveMediaSource {
        val uri = Uri.parse("assets:///${assetPath}")
        val dataSourceFactory = DataSource.Factory { AssetDataSource(context) }

        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(uri)
    }
}