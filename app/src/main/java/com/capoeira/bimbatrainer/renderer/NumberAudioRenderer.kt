package com.capoeira.bimbatrainer.renderer

import android.content.Context
import android.content.res.AssetManager
import com.capoeira.bimbatrainer.helper.AudioPathGenerator

class NumberAudioRenderer (audioPathGenerator : AudioPathGenerator?, assetManager: AssetManager) : AudioRenderer(assetManager) {

    var audioPathGenerator : AudioPathGenerator? = null

    var context : Context? = null

    init {
        this.audioPathGenerator = audioPathGenerator
    }


    override fun render(exerciseRenderItem: ExerciseRenderItem) {
        var numberFilePath = audioPathGenerator?.generateNumberAudioPath(exerciseRenderItem.sequenceNumber)
        if(!numberFilePath.isNullOrBlank()) {
            playAudio(numberFilePath)
        }
    }

}