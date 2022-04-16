package com.capoeira.bimbatrainer.renderer

import android.content.res.AssetManager
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import com.capoeira.bimbatrainer.helper.AudioPlayer

abstract class AudioRenderer(audioPlayer: AudioPlayer) : Renderer() {

    var audioPlayer: AudioPlayer

    init {
        this.audioPlayer = audioPlayer
    }

    fun playAudio(numberFilePath : String) {
        audioPlayer.playAudio(numberFilePath)
    }
}