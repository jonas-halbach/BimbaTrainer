package com.capoeira.bimbatrainer.helper

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import java.util.PriorityQueue
import java.util.Queue

class AudioPlayer {

    var audiosToPlay : Queue<AssetFileDescriptor>

    private var assetManager : AssetManager? = null

    var mediaPlayer: MediaPlayer? = null

    constructor(assetManager : AssetManager) {
        this.assetManager = assetManager
        audiosToPlay = PriorityQueue<AssetFileDescriptor>()
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setVolume(1.0f, 1.0f)
        mediaPlayer?.setOnCompletionListener(AudioOnCompletionListener(this))
    }

    fun playAudio(pathToAudioFile : String) {
        try {
            System.out.println("Play audio: " + pathToAudioFile)
            var audio = assetManager?.openFd(pathToAudioFile)
            audiosToPlay.add(audio)

            if (!mediaPlayer!!.isPlaying) {
                startNewAudio()
            }
        } catch (e: Exception) {
            //TODO: Handle properly
        }
    }

    @Synchronized
    fun startNewAudio() {

        var audiosLeftToPlay : Boolean = audiosToPlay.size > 0

        System.out.println("Start new audio: Audios left to play " + audiosLeftToPlay)
        if(audiosLeftToPlay) {
            var audio = audiosToPlay.remove()
            System.out.println("Start new audio: Audio to play is null " + audio == null)
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