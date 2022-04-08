package com.capoeira.bimbatrainer.helper

import android.content.res.AssetManager
import com.capoeira.bimbatrainer.enums.SequenceTypes
import com.capoeira.bimbatrainer.renderer.*

class AudioRendererFactory(audioPathGenerator: AudioPathGenerator?, assets: AssetManager) {

    var audioPathGenerator: AudioPathGenerator?
    var assets: AssetManager

    init{
        this.audioPathGenerator = audioPathGenerator
        this.assets = assets
    }

    fun createNumberAudioRenderer() : Renderer {
        return NumberAudioRenderer(audioPathGenerator, assets)
    }

    fun createSequenceTypeAudioRenderer(sequenceTypes : SequenceTypes) : Renderer {
        var sequenceTypeAudioRenderer : Renderer = NullRenderer()

        if(sequenceTypes == SequenceTypes.ALL) {
            sequenceTypeAudioRenderer = SequenceTypeAudioRenderer(audioPathGenerator, assets)
        }

        return sequenceTypeAudioRenderer
    }

    fun createSequencePartAudioRenderer(playerCount : Int) : Renderer{
        var playerCountAudioRenderer : Renderer = NullRenderer()

        if(playerCount == 1) {
            playerCountAudioRenderer = PlayerCountAudioRenderer(audioPathGenerator, assets)
        }

        return playerCountAudioRenderer
    }
}