package com.zaritcare.ui.features.activities

import android.content.Context
import android.media.MediaPlayer

class AudioPlayer(
    private val context: Context
) {
    private var player: MediaPlayer? = null

    fun play(auidoId: Int) {
        MediaPlayer.create(context, auidoId).apply {
            player = this
            start()
        }
    }

    fun stop() {
        player?.stop()
        player?.release()
        player = null
    }
}