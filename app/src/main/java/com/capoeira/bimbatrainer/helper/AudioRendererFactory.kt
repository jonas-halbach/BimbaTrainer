package com.capoeira.bimbatrainer.helper

import android.content.res.AssetManager
import com.capoeira.bimbatrainer.enums.SequenceTypes
import com.capoeira.bimbatrainer.renderer.*

class AudioRendererFactory(audioPathGenerator: AudioPathGenerator?, audioPlayer: AudioPlayer) {

    var audioPathGenerator: AudioPathGenerator?
    var audioPlayer: AudioPlayer

    init{
        this.audioPathGenerator = audioPathGenerator
        this.audioPlayer = audioPlayer
    }

    fun createNumberAudioRenderer() : Renderer {
        return NumberAudioRenderer(audioPathGenerator, audioPlayer)
    }

    fun createSequenceTypeAudioRenderer(sequenceTypes : SequenceTypes) : Renderer {
        var sequenceTypeAudioRenderer : Renderer = NullRenderer()

        if(sequenceTypes == SequenceTypes.ALL) {
            sequenceTypeAudioRenderer = SequenceTypeAudioRenderer(audioPathGenerator, audioPlayer)
        }

        return sequenceTypeAudioRenderer
    }

    fun createSequencePartAudioRenderer(playerCount : Int) : Renderer{
        var playerCountAudioRenderer : Renderer = NullRenderer()

        if(playerCount == 1) {
            playerCountAudioRenderer = PlayerCountAudioRenderer(audioPathGenerator, audioPlayer)
        }

        return playerCountAudioRenderer
    }
}