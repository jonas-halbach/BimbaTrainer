package com.capoeira.bimbatrainer.renderer

import android.content.res.AssetManager
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener

abstract class AudioRenderer : Renderer {

    private var assetManager : AssetManager? = null

    constructor(assetManager : AssetManager) {
        this.assetManager = assetManager
    }

    fun playAudio(pathToAudioFile : String) {
        try {
            var audio = assetManager?.openFd(pathToAudioFile)
            if (audio != null) {
                var mediaPlayer = MediaPlayer()

                mediaPlayer.setVolume(1.0f, 1.0f)
                mediaPlayer.setDataSource(audio.fileDescriptor, audio.startOffset, audio.length)
                mediaPlayer.prepare()
                mediaPlayer.start()
                mediaPlayer.setOnCompletionListener(AudioPlayBackDoneListener())
            }
        } catch (e : Exception) {
            //TODO: Handle properly
        }
    }
}

class AudioPlayBackDoneListener : OnCompletionListener {
    override fun onCompletion(p0: MediaPlayer?) {
        p0?.stop()
        p0?.reset()
        p0?.release()
    }

}