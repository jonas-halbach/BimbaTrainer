package com.capoeira.bimbatrainer.helper
import android.content.res.AssetManager
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import java.util.*

class AudioPlayer {

    var audiosToPlay : Queue<String>

    private var assetManager : AssetManager? = null

    var mediaPlayer: MediaPlayer? = null

    constructor(assetManager : AssetManager) {
        this.assetManager = assetManager
        audiosToPlay = LinkedList<String>()
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setVolume(1.0f, 1.0f)
        mediaPlayer?.setOnCompletionListener(AudioOnCompletionListener(this))
    }

    fun playAudio(pathToAudioFile : String) {
        try {
            System.out.println("Play audio: " + pathToAudioFile)

            audiosToPlay.add(pathToAudioFile)

            if (!mediaPlayer!!.isPlaying) {
                startNewAudio()
            }
        } catch (e: Exception) {
            //TODO: Handle properly
        }
    }

    fun startNewAudio() {
        synchronized(this) {
            var audiosLeftToPlay: Boolean = !audiosToPlay.isEmpty();

            System.out.println("Start new audio: Audios left to play " + audiosLeftToPlay)
            if (audiosLeftToPlay) {
                var pathToAudioFile = audiosToPlay.remove()
                var audio = assetManager?.openFd(pathToAudioFile)
                System.out.println("Start new audio: Audio to play is null " + pathToAudioFile)
                if (audio != null) {
                    try {
                        mediaPlayer?.setDataSource(audio.fileDescriptor, audio.startOffset, audio.length)
                        mediaPlayer?.prepare()
                        mediaPlayer?.start()
                    } catch (e: Exception) {
                        //TODO: Handle properly
                    }
                }
            }
        }
    }
}

class AudioOnCompletionListener : OnCompletionListener {

    var owner : AudioPlayer

    constructor(owner: AudioPlayer) {
        this.owner = owner
    }

    override fun onCompletion(p0: MediaPlayer?) {
        System.out.println("onComplete!")

        //p0?.stop()
        p0?.reset()
        //p0?.release()
        owner.startNewAudio()
    }
}