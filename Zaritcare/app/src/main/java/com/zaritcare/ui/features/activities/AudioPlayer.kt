package com.zaritcare.ui.features.activities

import android.content.Context
import android.media.MediaPlayer

class AudioPlayer(
    private val context: Context
) {
    private var player: MediaPlayer? = null

    fun play(auidoId: Int) {
        if(player == null) {
            MediaPlayer.create(context, auidoId).apply {
                player = this
                start()
            }
        } else {
            player?.start()
        }
    }

    fun stop() {
        player?.stop()
        player?.release()
        player = null
    }

    fun pause() {
        player?.pause()
    }

    fun isPlaying(): Boolean = player != null
}